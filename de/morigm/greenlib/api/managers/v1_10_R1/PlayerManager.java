package de.morigm.greenlib.api.managers.v1_10_R1;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_10_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_10_R1.util.CraftMagicNumbers;
import org.bukkit.craftbukkit.v1_10_R1.util.CraftChatMessage;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.mojang.authlib.GameProfile;

import de.morigm.greenlib.Main;
import de.morigm.greenlib.api.builder.GameProfileBuilder;
import de.morigm.greenlib.api.enums.DemoScreenType;
import de.morigm.greenlib.api.enums.EquipmentType;
import de.morigm.greenlib.api.events.packet.PacketPlayOutEvent;
import de.morigm.greenlib.api.gui.GUI;
import de.morigm.greenlib.api.listener.SignListener;
import de.morigm.greenlib.api.packet.playout.PacketPlayOutAbilities;
import de.morigm.greenlib.api.packet.playout.PacketPlayOutAnimation;
import de.morigm.greenlib.api.packet.playout.PacketPlayOutBed;
import de.morigm.greenlib.api.packet.playout.PacketPlayOutBlockAction;
import de.morigm.greenlib.api.packet.playout.PacketPlayOutBlockBreakAnimation;
import de.morigm.greenlib.api.packet.playout.PacketPlayOutBlockChange;
import de.morigm.greenlib.api.packet.playout.PacketPlayOutBoss;
import de.morigm.greenlib.api.packet.playout.PacketPlayOutCamera;
import de.morigm.greenlib.api.packet.playout.PacketPlayOutChat;
import de.morigm.greenlib.api.packet.playout.PacketPlayOutCloseWindow;
import de.morigm.greenlib.api.packet.playout.PacketPlayOutCollect;
import de.morigm.greenlib.api.packet.playout.PacketPlayOutCombatEvent;
import de.morigm.greenlib.api.packet.playout.PacketPlayOutCustomPayload;
import de.morigm.greenlib.api.packet.playout.PacketPlayOutCustomSoundEffect;
import de.morigm.greenlib.api.packet.playout.PacketPlayOutEntity;
import de.morigm.greenlib.api.packet.playout.PacketPlayOutEntityEffect;
import de.morigm.greenlib.api.packet.playout.PacketPlayOutEntityLook;
import de.morigm.greenlib.api.packet.playout.PacketPlayOutRelEntityMoveLook;
import de.morigm.greenlib.api.packet.types.PacketPlayOut;
import de.morigm.greenlib.api.uuid.UUIDFetcher;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.server.v1_10_R1.BlockPosition;
import net.minecraft.server.v1_10_R1.EnumItemSlot;
import net.minecraft.server.v1_10_R1.IChatBaseComponent;
import net.minecraft.server.v1_10_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_10_R1.Packet;
import net.minecraft.server.v1_10_R1.PacketPlayInClientCommand;
import net.minecraft.server.v1_10_R1.PacketPlayInClientCommand.EnumClientCommand;
import net.minecraft.server.v1_10_R1.PacketPlayInUpdateSign;
import net.minecraft.server.v1_10_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_10_R1.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_10_R1.PacketPlayOutGameStateChange;
import net.minecraft.server.v1_10_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_10_R1.PacketPlayOutOpenSignEditor;
import net.minecraft.server.v1_10_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_10_R1.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.server.v1_10_R1.MobEffect;
import net.minecraft.server.v1_10_R1.MobEffectList;
import net.minecraft.server.v1_10_R1.SoundCategory;
import net.minecraft.server.v1_10_R1.PacketDataSerializer;
import net.minecraft.server.v1_10_R1.CombatTracker;
import net.minecraft.server.v1_10_R1.EntityLiving;
import net.minecraft.server.v1_10_R1.PacketPlayOutCombatEvent.EnumCombatEventType;
import net.minecraft.server.v1_10_R1.BossBattle;
import net.minecraft.server.v1_10_R1.BossBattle.BarColor;
import net.minecraft.server.v1_10_R1.BossBattle.BarStyle;
import net.minecraft.server.v1_10_R1.PlayerAbilities;


public class PlayerManager extends de.morigm.greenlib.api.managers.PlayerManager {
	

	private Channel channel;

	public PlayerManager(Player p)
	{
		super(p);
	}

	@Override
	public void sendMessage(String s)
	{
		player.sendMessage(s);
	}

	@Override
	public void sendActionBar(String s) 
	{
		CraftPlayer cp = (CraftPlayer) player;
		
		IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + s +"\"}");
		net.minecraft.server.v1_10_R1.PacketPlayOutChat bar = new net.minecraft.server.v1_10_R1.PacketPlayOutChat(icbc, (byte)2);
		cp.getHandle().playerConnection.sendPacket(bar);
	}

	@Override
	public int getPing() 
	{
		CraftPlayer cp = (CraftPlayer) player;
		return cp.getHandle().ping;
	}

	@Override
	public void sendMessage(String... s) 
	{
		player.sendMessage(s);
	}

	@Override
	public void openGUI(GUI gui) 
	{
		gui.load();
		gui.loadButtons();
		gui.open();
		Main.getInstance().getDefaultConfig().guis.put(player, gui);
	}
	
	@Override
	public void openSignGui(SignListener sl) 
	{
		CraftPlayer cp = (CraftPlayer) player;
		Channel ch = cp.getHandle().playerConnection.networkManager.channel;
		this.channel = ch;
		ch.pipeline().addAfter("decoder", "SignListener", new MessageToMessageDecoder<Packet<?>>()
		{

			@Override
			protected void decode(ChannelHandlerContext arg0, Packet<?> packet, List<Object> arg2) throws Exception 
			{
				arg2.add(packet);
				if(packet instanceof PacketPlayInUpdateSign)
				{
					PacketPlayInUpdateSign ppius = (PacketPlayInUpdateSign) packet;
					new BukkitRunnable() 
					{
						
						@Override
						public void run() 
						{
							sl.onListener(ppius.b());
						}
						
					}.runTaskLater(Main.getInstance(), 1);
					channel.pipeline().remove("SignListener");
				}
			}
		});
		PacketPlayOutOpenSignEditor packet = new PacketPlayOutOpenSignEditor(new BlockPosition(0, -1, 0));
		cp.getHandle().playerConnection.sendPacket(packet);
	}
	
	@Override
	public void sendEquipment(Entity e, EquipmentType type, ItemStack item) 
	{
		if(type.equals(EquipmentType.MAINHAND))
		{
			PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(e.getEntityId(), EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(item));
			CraftPlayer cp = (CraftPlayer) player;
			cp.getHandle().playerConnection.sendPacket(packet);
		}
		if(type.equals(EquipmentType.OFFHAND))
		{
			PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(e.getEntityId(), EnumItemSlot.OFFHAND, CraftItemStack.asNMSCopy(item));
			CraftPlayer cp = (CraftPlayer) player;
			cp.getHandle().playerConnection.sendPacket(packet);
		}
		if(type.equals(EquipmentType.HAND))
		{
			PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(e.getEntityId(), EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(item));
			CraftPlayer cp = (CraftPlayer) player;
			cp.getHandle().playerConnection.sendPacket(packet);
		}
		if(type.equals(EquipmentType.FEET))
		{
			PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(e.getEntityId(), EnumItemSlot.FEET, CraftItemStack.asNMSCopy(item));
			CraftPlayer cp = (CraftPlayer) player;
			cp.getHandle().playerConnection.sendPacket(packet);
		}
		if(type.equals(EquipmentType.LEGS))
		{
			PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(e.getEntityId(), EnumItemSlot.LEGS, CraftItemStack.asNMSCopy(item));
			CraftPlayer cp = (CraftPlayer) player;
			cp.getHandle().playerConnection.sendPacket(packet);
		}
		if(type.equals(EquipmentType.CHEST))
		{
			PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(e.getEntityId(), EnumItemSlot.CHEST, CraftItemStack.asNMSCopy(item));
			CraftPlayer cp = (CraftPlayer) player;
			cp.getHandle().playerConnection.sendPacket(packet);
		}
		if(type.equals(EquipmentType.HEAD))
		{
			PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(e.getEntityId(), EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(item));
			CraftPlayer cp = (CraftPlayer) player;
			cp.getHandle().playerConnection.sendPacket(packet);
		}
	}
	
	@Override
	public void openDemoScreen(DemoScreenType type) 
	{
		if(type.equals(DemoScreenType.WELCOME))
		{
			PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(5, 0);
			CraftPlayer cp = (CraftPlayer) player;
			cp.getHandle().playerConnection.sendPacket(packet);
		}
		if(type.equals(DemoScreenType.MOVEMENT))
		{
			PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(5, 101);
			CraftPlayer cp = (CraftPlayer) player;
			cp.getHandle().playerConnection.sendPacket(packet);
		}
		if(type.equals(DemoScreenType.JUMP))
		{
			PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(5, 102);
			CraftPlayer cp = (CraftPlayer) player;
			cp.getHandle().playerConnection.sendPacket(packet);
		}
		if(type.equals(DemoScreenType.INVENTORY))
		{
			PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(5, 103);
			CraftPlayer cp = (CraftPlayer) player;
			cp.getHandle().playerConnection.sendPacket(packet);
		}
	}

	@Override
	public void openEndScreen() 
	{
		PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(4, 1);
		CraftPlayer cp = (CraftPlayer) player;
		cp.getHandle().playerConnection.sendPacket(packet);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void sendPacket(PacketPlayOut packet) 
	{
		PacketPlayOutEvent event = new PacketPlayOutEvent(player, packet);
		Bukkit.getPluginManager().callEvent(event);
		CraftPlayer cp = (CraftPlayer) player;
		Packet<?> pack = null;
		if(packet instanceof PacketPlayOutAbilities)
		{
			PacketPlayOutAbilities p = (PacketPlayOutAbilities) packet;
			PlayerAbilities pa = new PlayerAbilities();
			pa.canFly = p.isCanFly();
			pa.canInstantlyBuild = p.isInstantlyBuild();
			pa.isFlying = p.isFlying();
			pa.isInvulnerable = p.isInvulnerable();
			pack = new net.minecraft.server.v1_10_R1.PacketPlayOutAbilities(pa);
		}
		if(packet instanceof PacketPlayOutAnimation)
		{
			PacketPlayOutAnimation pa = (PacketPlayOutAnimation) packet;
			pack = new net.minecraft.server.v1_10_R1.PacketPlayOutAnimation(((CraftEntity)pa.a()).getHandle(), pa.b());
		}
		if(packet instanceof PacketPlayOutBed)
		{
			PacketPlayOutBed pa = (PacketPlayOutBed) packet;
			pack = new net.minecraft.server.v1_10_R1.PacketPlayOutBed(((CraftPlayer)pa.a()).getHandle(),new BlockPosition(pa.b().getX(), pa.b().getY(), pa.b().getZ()));
		}
		if(packet instanceof PacketPlayOutBlockAction)
		{
			PacketPlayOutBlockAction pa = (PacketPlayOutBlockAction) packet;
			pack = new net.minecraft.server.v1_10_R1.PacketPlayOutBlockAction(new BlockPosition(pa.a().getX(), pa.a().getY(), pa.a().getZ()),(net.minecraft.server.v1_10_R1.Block) pa.d().getState(),pa.b(),pa.c());
		}
		if(packet instanceof PacketPlayOutBlockBreakAnimation)
		{
			PacketPlayOutBlockBreakAnimation pa = (PacketPlayOutBlockBreakAnimation) packet;
			pack = new net.minecraft.server.v1_10_R1.PacketPlayOutBlockBreakAnimation(pa.getEntityID(), new BlockPosition(pa.getLocation().getX(), pa.getLocation().getY(), pa.getLocation().getZ()), pa.getCount());
		}
		if(packet instanceof PacketPlayOutBlockChange)
		{
			PacketPlayOutBlockChange pa = (PacketPlayOutBlockChange) packet;
			pack = new net.minecraft.server.v1_10_R1.PacketPlayOutBlockChange(((CraftWorld)pa.getWorld()).getHandle(),new BlockPosition(pa.getLocation().getX(), pa.getLocation().getY(), pa.getLocation().getZ()));
			((net.minecraft.server.v1_10_R1.PacketPlayOutBlockChange)pack).block = CraftMagicNumbers.getBlock(pa.getMaterial().getId()).fromLegacyData(pa.getData());
		}
		if(packet instanceof PacketPlayOutBoss)
		{
			PacketPlayOutBoss pa = (PacketPlayOutBoss) packet;
			net.minecraft.server.v1_10_R1.PacketPlayOutBoss.Action action = net.minecraft.server.v1_10_R1.PacketPlayOutBoss.Action.valueOf(pa.getAction().name().toUpperCase());
			BossBattle battle = new BossBattle(pa.getBattle().getUuid(), CraftChatMessage.fromString(pa.getBattle().getText(), true)[0], BarColor.valueOf(pa.getBattle().getColor().name().toUpperCase()), BarStyle.valueOf(pa.getBattle().getStyle().name().toUpperCase())) {};
			battle.a(pa.getBattle().getProgress());
			pack = new net.minecraft.server.v1_10_R1.PacketPlayOutBoss(action, battle);
		}
		if(packet instanceof PacketPlayOutCamera)
		{
			PacketPlayOutCamera pa = (PacketPlayOutCamera) packet;
			CraftEntity ce = (CraftEntity) pa.getEntity();
			pack = new net.minecraft.server.v1_10_R1.PacketPlayOutCamera(ce.getHandle());
		}
		if(packet instanceof PacketPlayOutChat)
		{
			PacketPlayOutChat pa = (PacketPlayOutChat) packet;
			String message = pa.getText();
			IChatBaseComponent[] arrayOfIChatBaseComponent;
		    int i = (arrayOfIChatBaseComponent = CraftChatMessage.fromString(message)).length;
		    for (int j = 0; j < i - 1 ; j++)
		    {
		      IChatBaseComponent component = arrayOfIChatBaseComponent[j];
		      cp.getHandle().playerConnection.sendPacket(new net.minecraft.server.v1_10_R1.PacketPlayOutChat(component));
		    }
		    IChatBaseComponent component = arrayOfIChatBaseComponent[i - 1];
		    pack = new net.minecraft.server.v1_10_R1.PacketPlayOutChat(component);
		}
		if(packet instanceof PacketPlayOutCloseWindow)
		{
			PacketPlayOutCloseWindow pa = (PacketPlayOutCloseWindow) packet;
			pack = new net.minecraft.server.v1_10_R1.PacketPlayInCloseWindow(pa.getWindow());
		}
		if(packet instanceof PacketPlayOutCollect)
		{
			PacketPlayOutCollect pa = (PacketPlayOutCollect) packet;
			pack = new net.minecraft.server.v1_10_R1.PacketPlayOutCollect(pa.getCollectorEntityID(), pa.getPickupItemCount());
		}
		if(packet instanceof PacketPlayOutCombatEvent)
		{
			PacketPlayOutCombatEvent pa = (PacketPlayOutCombatEvent) packet;
			CraftEntity entity = (CraftEntity) pa.getCombatTracker().getEntity();
			EntityLiving entityLiving = (EntityLiving) entity.getHandle();
			pack = new net.minecraft.server.v1_10_R1.PacketPlayOutCombatEvent(new CombatTracker(entityLiving),EnumCombatEventType.valueOf(pa.getType().name().toUpperCase()),pa.is());
		}
		if(packet instanceof PacketPlayOutCustomPayload)
		{
			PacketPlayOutCustomPayload pa = (PacketPlayOutCustomPayload) packet;
			PacketDataSerializer packetDataSerializer = ((de.morigm.greenlib.api.buffer.v1_10_R1.PacketBuffer)pa.getPacketBuffer()).getPacketDataSerializer();
			pack = new net.minecraft.server.v1_10_R1.PacketPlayOutCustomPayload(pa.getChannel(), packetDataSerializer);
		}
		if(packet instanceof PacketPlayOutCustomSoundEffect)
		{
			PacketPlayOutCustomSoundEffect pa = (PacketPlayOutCustomSoundEffect) packet;
			SoundCategory soundCategory = SoundCategory.valueOf(pa.getSoundCategory().name().toUpperCase());
			pack = new net.minecraft.server.v1_10_R1.PacketPlayOutCustomSoundEffect(pa.getSoundId(), soundCategory, pa.getX(), pa.getY(), pa.getZ(), pa.getVolume(), pa.getPitch());
		}
		if(packet instanceof PacketPlayOutEntity)
		{
			PacketPlayOutEntity pa = (PacketPlayOutEntity) packet;
			pack = new net.minecraft.server.v1_10_R1.PacketPlayOutEntity(pa.getEntityId());
		}
		if(packet instanceof PacketPlayOutRelEntityMoveLook)
		{
			PacketPlayOutRelEntityMoveLook pa = (PacketPlayOutRelEntityMoveLook) packet;
			pack = new net.minecraft.server.v1_10_R1.PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook(pa.getEntityId(), pa.getDeltaX(), pa.getDeltaY(), pa.getDeltaZ(), pa.getPitch(), pa.getYaw(), pa.isOnGround());
		}
		if(packet instanceof PacketPlayOutRelEntityMoveLook)
		{
			PacketPlayOutRelEntityMoveLook  pa = (PacketPlayOutRelEntityMoveLook) packet;
			pack = new net.minecraft.server.v1_10_R1.PacketPlayOutEntity.PacketPlayOutRelEntityMove(pa.getEntityId(), pa.getDeltaX(), pa.getDeltaY(), pa.getDeltaZ(), pa.isOnGround());
		}
		if(packet instanceof PacketPlayOutEntityLook)
		{
			PacketPlayOutEntityLook pa = (PacketPlayOutEntityLook) packet;
			pack = new net.minecraft.server.v1_10_R1.PacketPlayOutEntity.PacketPlayOutEntityLook(pa.getEntityId(), pa.getPitch(), pa.getYaw(), pa.isOnGround());
		}
		if(packet instanceof de.morigm.greenlib.api.packet.playout.PacketPlayOutEntityDestroy)
		{
			de.morigm.greenlib.api.packet.playout.PacketPlayOutEntityDestroy pa = (de.morigm.greenlib.api.packet.playout.PacketPlayOutEntityDestroy) packet;
			pack = new PacketPlayOutEntityDestroy(pa.getEntitysID());
		}
		if(packet instanceof PacketPlayOutEntityEffect)
		{
			PacketPlayOutEntityEffect pa = (PacketPlayOutEntityEffect) packet;
			pack = new net.minecraft.server.v1_10_R1.PacketPlayOutEntityEffect(pa.getParamint(), new MobEffect(MobEffectList.fromId(pa.getMobEffect().getMobEffectList().getEffectID()), pa.getMobEffect().getI(), pa.getMobEffect().getX(), pa.getMobEffect().isParm1(), pa.getMobEffect().isParm2()));
		}
		if(packet instanceof de.morigm.greenlib.api.packet.playout.PacketPlayOutEntityEquipment)
		{
			de.morigm.greenlib.api.packet.playout.PacketPlayOutEntityEquipment pa = (de.morigm.greenlib.api.packet.playout.PacketPlayOutEntityEquipment) packet;
			pack = new PacketPlayOutEntityEquipment(pa.getEntityID(), EnumItemSlot.valueOf(pa.getEquipmentType().name().toUpperCase()), CraftItemStack.asNMSCopy(pa.getItemStack()));
		}
		cp.getHandle().playerConnection.sendPacket(pack);
	}
	
	@Override
	public void setName(String name) 
	{
		Main.getInstance().getDefaultConfig().names.add(player);
		CraftPlayer cp = (CraftPlayer) player;
		Field f;
		try 
		{
			f = cp.getHandle().getProfile().getClass().getDeclaredField("name");
			f.setAccessible(true);
			f.set(cp.getHandle().getProfile(), name);
		} 
		catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
		{	
			e.printStackTrace();
		}
		for(Player nom : Bukkit.getOnlinePlayers())
		{
			CraftPlayer nomcp = (CraftPlayer) nom;
			nomcp.getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER, cp.getHandle()));
		}
		for(Player nom : Bukkit.getOnlinePlayers())
		{
			CraftPlayer nomcp = (CraftPlayer) nom;
			nomcp.getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(cp.getHandle().getId()));
		}
		
		double live = cp.getHealth();
		Location loc = cp.getLocation();
		
		cp.setHealth(0);
		new BukkitRunnable() 
		{
			
			@Override
			public void run() 
			{
				cp.getHandle().playerConnection.a(new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN));
				for(Player nom : Bukkit.getOnlinePlayers())
				{
					CraftPlayer nomcp = (CraftPlayer) nom;
					nomcp.getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, cp.getHandle()));
				}
				for(Player nom : Bukkit.getOnlinePlayers())
				{
					if(nom == player)
						continue;
					CraftPlayer nomcp = (CraftPlayer) nom;
					nomcp.getHandle().playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(cp.getHandle()));
				}
				cp.setHealth(live);
				cp.teleport(loc);
			}
		}.runTaskLater(Main.getInstance(), 4L);
	}

	@Override
	public void removeName() 
	{
		try {
			this.setName(GameProfileBuilder.fetch(player.getUniqueId()).getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setSkin(UUID uuid) 
	{
		Main.getInstance().getDefaultConfig().skins.add(player);
		CraftPlayer cp = (CraftPlayer) player;
		cp.getHandle().getProfile().getProperties().removeAll("textures");
		GameProfile gm = null;
		try
		{
			gm = GameProfileBuilder.fetch(uuid);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		cp.getHandle().getProfile().getProperties().putAll("textures",gm.getProperties().get("textures"));
		for(Player nom : Bukkit.getOnlinePlayers())
		{
			CraftPlayer nomcp = (CraftPlayer) nom;
			nomcp.getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER, cp.getHandle()));
		}
		for(Player nom : Bukkit.getOnlinePlayers())
		{
			CraftPlayer nomcp = (CraftPlayer) nom;
			nomcp.getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(cp.getHandle().getId()));
		}
		
		double live = cp.getHealth();
		Location loc = cp.getLocation();
		
		cp.setHealth(0);
		new BukkitRunnable() 
		{
			
			@Override
			public void run() 
			{
				cp.getHandle().playerConnection.a(new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN));
				for(Player nom : Bukkit.getOnlinePlayers())
				{
					CraftPlayer nomcp = (CraftPlayer) nom;
					nomcp.getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, cp.getHandle()));
				}
				for(Player nom : Bukkit.getOnlinePlayers())
				{
					if(nom == player)
						continue;
					CraftPlayer nomcp = (CraftPlayer) nom;
					nomcp.getHandle().playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(cp.getHandle()));
				}
				cp.setHealth(live);
				cp.teleport(loc);
			}
		}.runTaskLater(Main.getInstance(), 4L);
	}

	@Override
	public void removeSkin() 
	{
		this.setSkin(player.getUniqueId());
	}

	@Override
	public void setSkinAndName(String name) 
	{
		Main.getInstance().getDefaultConfig().skins.add(player);
		CraftPlayer cp = (CraftPlayer) player;
		cp.getHandle().getProfile().getProperties().removeAll("textures");
		GameProfile gm = null;
		try
		{
			gm = GameProfileBuilder.fetch(UUIDFetcher.getUUID(name));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Field f;
		try 
		{
			f = cp.getHandle().getProfile().getClass().getDeclaredField("name");
			f.setAccessible(true);
			f.set(cp.getHandle().getProfile(), name);
		} 
		catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
		{	
			e.printStackTrace();
		}
		cp.getHandle().getProfile().getProperties().putAll("textures",gm.getProperties().get("textures"));
		for(Player nom : Bukkit.getOnlinePlayers())
		{
			CraftPlayer nomcp = (CraftPlayer) nom;
			nomcp.getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER, cp.getHandle()));
		}
		for(Player nom : Bukkit.getOnlinePlayers())
		{
			CraftPlayer nomcp = (CraftPlayer) nom;
			nomcp.getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(cp.getHandle().getId()));
		}
		
		double live = cp.getHealth();
		Location loc = cp.getLocation();
		
		cp.setHealth(0);
		new BukkitRunnable() 
		{
			
			@Override
			public void run() 
			{
				cp.getHandle().playerConnection.a(new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN));
				for(Player nom : Bukkit.getOnlinePlayers())
				{
					CraftPlayer nomcp = (CraftPlayer) nom;
					nomcp.getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, cp.getHandle()));
				}
				for(Player nom : Bukkit.getOnlinePlayers())
				{
					if(nom == player)
						continue;
					CraftPlayer nomcp = (CraftPlayer) nom;
					nomcp.getHandle().playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(cp.getHandle()));
				}
				cp.setHealth(live);
				cp.teleport(loc);
			}
		}.runTaskLater(Main.getInstance(), 4L);
	}

	@Override
	public void removeSkinAndName() 
	{
		setSkinAndName(UUIDFetcher.getName(player.getUniqueId()));
	}
	
	@Override
	public GameProfile getGameProfile() 
	{
		CraftPlayer cp = (CraftPlayer) player;
		return cp.getHandle().getProfile();
	}

}
