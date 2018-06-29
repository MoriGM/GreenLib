package de.morigm.greenlib.packetlistener.v1_8_R3;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import de.morigm.greenlib.Main;
import de.morigm.greenlib.api.GreenLib;
import de.morigm.greenlib.api.enums.EnumDirection;
import de.morigm.greenlib.api.enums.EnumHand;
import de.morigm.greenlib.api.enums.EnumPlayerDigType;
import de.morigm.greenlib.api.packet.playin.async.AsyncPacketPlayInArmAnimation;
import de.morigm.greenlib.api.packet.playin.async.AsyncPacketPlayInBlockDig;
import de.morigm.greenlib.api.packet.server.ServerPlayOutAbilities;
import de.morigm.greenlib.api.packet.types.PacketPlayIn;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import net.minecraft.server.v1_8_R3.PacketPlayOutAbilities;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.NetworkManager;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInAbilities;
import net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation;

public class ListenerPacket extends de.morigm.greenlib.packetlistener.ListenerPacket {
	
	private PlayerConnection playerConnection_old;

	public ListenerPacket(Player p) 
	{
		super(p);
	}

	@Override
	public void init() 
	{
		CraftPlayer cp = (CraftPlayer) super.player;
		playerConnection_old = cp.getHandle().playerConnection;
		new PlayerConnector(cp.getHandle().server, cp.getHandle().playerConnection.networkManager, cp.getHandle());
		Channel ch = cp.getHandle().playerConnection.networkManager.channel;
		ch.pipeline().addAfter("decoder", "PacketListner", new MessageToMessageDecoder<Packet<?>>() 
		{

			@Override
			protected void decode(ChannelHandlerContext arg0, Packet<?> arg1, List<Object> arg2) throws Exception 
			{
				startlistener(arg1);
				boolean close = listenAsync(arg1);
				if(!close)
					arg2.add(arg1);
			}

			private void startlistener(Packet<?> p) 
			{
				new BukkitRunnable() 
				{
					
					@Override
					public void run() 
					{
						listen(p);
					}
				}.runTaskLater(Main.getInstance(), 1);
			}
		});
	}

	@Override
	public void close() 
	{
		CraftPlayer cp = (CraftPlayer) super.player;
		Channel ch = cp.getHandle().playerConnection.networkManager.channel;
		if(ch.pipeline().get("PacketListner") != null)
			ch.pipeline().remove("PacketListner");
		if(playerConnection_old != null)
			cp.getHandle().playerConnection = playerConnection_old;
	}
	
	@Override
	public boolean isInit() 
	{
		CraftPlayer cp = (CraftPlayer) super.player;
		Channel ch = cp.getHandle().playerConnection.networkManager.channel;
		return ch.pipeline().get("PacketListner") != null && playerConnection_old != null;
	}
	
	public boolean listenAsync(Packet<?> p) 
	{
		boolean close = false;
		if(p instanceof PacketPlayInAbilities)
		{
			PacketPlayInAbilities pa = (PacketPlayInAbilities) p;
			de.morigm.greenlib.api.packet.playin.async.AsyncPacketPlayInAbilities packet = new de.morigm.greenlib.api.packet.playin.async.AsyncPacketPlayInAbilities();
			packet.setPlayer(player);
			packet.a(pa.a());
			packet.b(pa.isFlying());
			packet.c(pa.c());
			packet.d(pa.d());
			call(packet);
			pa.a(packet.a());
			pa.b(packet.isFlying());
			pa.c(packet.c());
			pa.d(packet.d());
			close = packet.isCanceled();
		}
		if(p instanceof PacketPlayInArmAnimation)
		{
			AsyncPacketPlayInArmAnimation packet = new AsyncPacketPlayInArmAnimation();
			packet.setPlayer(player);
			packet.a(EnumHand.MAIN_HAND);
			call(packet);
			close = packet.isCanceled();
		}
		if(p instanceof PacketPlayInBlockDig)
		{
			PacketPlayInBlockDig pa = (PacketPlayInBlockDig) p;
			EnumDirection d = null;
			if(pa.b().equals(net.minecraft.server.v1_8_R3.EnumDirection.DOWN))
				d = EnumDirection.DOWN;
			if(pa.b().equals(net.minecraft.server.v1_8_R3.EnumDirection.UP))
				d = EnumDirection.UP;
			if(pa.b().equals(net.minecraft.server.v1_8_R3.EnumDirection.EAST))
				d = EnumDirection.EAST;
			if(pa.b().equals(net.minecraft.server.v1_8_R3.EnumDirection.WEST))
				d = EnumDirection.WEST;
			if(pa.b().equals(net.minecraft.server.v1_8_R3.EnumDirection.NORTH))
				d = EnumDirection.NORTH;
			if(pa.b().equals(net.minecraft.server.v1_8_R3.EnumDirection.SOUTH))
				d = EnumDirection.SOUTH;
			EnumPlayerDigType epdt = null;
			if(pa.c().equals(net.minecraft.server.v1_8_R3.PacketPlayInBlockDig.EnumPlayerDigType.ABORT_DESTROY_BLOCK))
				epdt = EnumPlayerDigType.ABORT_DESTROY_BLOCK;
			if(pa.c().equals(net.minecraft.server.v1_8_R3.PacketPlayInBlockDig.EnumPlayerDigType.DROP_ALL_ITEMS))
				epdt = EnumPlayerDigType.DROP_ALL_ITEMS;
			if(pa.c().equals(net.minecraft.server.v1_8_R3.PacketPlayInBlockDig.EnumPlayerDigType.DROP_ITEM))
				epdt = EnumPlayerDigType.DROP_ITEM;
			if(pa.c().equals(net.minecraft.server.v1_8_R3.PacketPlayInBlockDig.EnumPlayerDigType.RELEASE_USE_ITEM))
				epdt = EnumPlayerDigType.RELEASE_USE_ITEM;
			if(pa.c().equals(net.minecraft.server.v1_8_R3.PacketPlayInBlockDig.EnumPlayerDigType.START_DESTROY_BLOCK))
				epdt = EnumPlayerDigType.START_DESTROY_BLOCK;
			if(pa.c().equals(net.minecraft.server.v1_8_R3.PacketPlayInBlockDig.EnumPlayerDigType.STOP_DESTROY_BLOCK))
				epdt = EnumPlayerDigType.STOP_DESTROY_BLOCK;
			AsyncPacketPlayInBlockDig packet = new AsyncPacketPlayInBlockDig(new Location(player.getWorld(), pa.a().getX(), pa.a().getY(), pa.a().getZ()), d,epdt);
			packet.setPlayer(player);
			call(packet);
			close = packet.isCancelled();
		}
		return close;
	}
	
	public void listen(Packet<?> p)
	{
		if(p instanceof PacketPlayInAbilities)
		{
			PacketPlayInAbilities pa = (PacketPlayInAbilities) p;
			de.morigm.greenlib.api.packet.playin.sync.PacketPlayInAbilities packet = new de.morigm.greenlib.api.packet.playin.sync.PacketPlayInAbilities();
			packet.setPlayer(player);
			packet.a(pa.a());
			packet.b(pa.isFlying());
			packet.c(pa.c());
			packet.d(pa.d());
			call(packet);
		}
		if(p instanceof PacketPlayInArmAnimation)
		{
			de.morigm.greenlib.api.packet.playin.sync.PacketPlayInArmAnimation packet = new de.morigm.greenlib.api.packet.playin.sync.PacketPlayInArmAnimation();
			packet.setPlayer(player);
			packet.a(EnumHand.MAIN_HAND);
			call(packet);
		}
		if(p instanceof PacketPlayInBlockDig)
		{
			PacketPlayInBlockDig pa = (PacketPlayInBlockDig) p;
			EnumDirection d = null;
			if(pa.b().equals(net.minecraft.server.v1_8_R3.EnumDirection.DOWN))
				d = EnumDirection.DOWN;
			if(pa.b().equals(net.minecraft.server.v1_8_R3.EnumDirection.UP))
				d = EnumDirection.UP;
			if(pa.b().equals(net.minecraft.server.v1_8_R3.EnumDirection.EAST))
				d = EnumDirection.EAST;
			if(pa.b().equals(net.minecraft.server.v1_8_R3.EnumDirection.WEST))
				d = EnumDirection.WEST;
			if(pa.b().equals(net.minecraft.server.v1_8_R3.EnumDirection.NORTH))
				d = EnumDirection.NORTH;
			if(pa.b().equals(net.minecraft.server.v1_8_R3.EnumDirection.SOUTH))
				d = EnumDirection.SOUTH;
			EnumPlayerDigType epdt = null;
			if(pa.c().equals(net.minecraft.server.v1_8_R3.PacketPlayInBlockDig.EnumPlayerDigType.ABORT_DESTROY_BLOCK))
				epdt = EnumPlayerDigType.ABORT_DESTROY_BLOCK;
			if(pa.c().equals(net.minecraft.server.v1_8_R3.PacketPlayInBlockDig.EnumPlayerDigType.DROP_ALL_ITEMS))
				epdt = EnumPlayerDigType.DROP_ALL_ITEMS;
			if(pa.c().equals(net.minecraft.server.v1_8_R3.PacketPlayInBlockDig.EnumPlayerDigType.DROP_ITEM))
				epdt = EnumPlayerDigType.DROP_ITEM;
			if(pa.c().equals(net.minecraft.server.v1_8_R3.PacketPlayInBlockDig.EnumPlayerDigType.RELEASE_USE_ITEM))
				epdt = EnumPlayerDigType.RELEASE_USE_ITEM;
			if(pa.c().equals(net.minecraft.server.v1_8_R3.PacketPlayInBlockDig.EnumPlayerDigType.START_DESTROY_BLOCK))
				epdt = EnumPlayerDigType.START_DESTROY_BLOCK;
			if(pa.c().equals(net.minecraft.server.v1_8_R3.PacketPlayInBlockDig.EnumPlayerDigType.STOP_DESTROY_BLOCK))
				epdt = EnumPlayerDigType.STOP_DESTROY_BLOCK;
			de.morigm.greenlib.api.packet.playin.sync.PacketPlayInBlockDig packet = new de.morigm.greenlib.api.packet.playin.sync.PacketPlayInBlockDig(new Location(player.getWorld(), pa.a().getX(), pa.a().getY(), pa.a().getZ()), d,epdt);
			packet.setPlayer(player);
			call(packet);
		}
	}
	
	public void call(PacketPlayIn ppi)
	{
		GreenLib.getPacketManager().onPacket(ppi);
	}
	
	public class PlayerConnector extends PlayerConnection
	{

		public PlayerConnector(MinecraftServer minecraftserver, NetworkManager networkmanager, EntityPlayer entityplayer) 
		{
			super(minecraftserver, networkmanager, entityplayer);
		}
		
		@SuppressWarnings("rawtypes")
		@Override
		public void sendPacket(final Packet packet)
		{
			super.sendPacket(packet);
			onPacket(packet);
		}

		private void onPacket(Packet<?> pack) 
		{
			if(pack instanceof PacketPlayOutAbilities)
			{
				PacketPlayOutAbilities packet = (PacketPlayOutAbilities) pack;
				ServerPlayOutAbilities serverPlayOutAbilities = new ServerPlayOutAbilities(getPlayer(),packet.a(), packet.b(), packet.c(), packet.d());
				GreenLib.getPacketManager().onPacket(serverPlayOutAbilities);
				packet.a(serverPlayOutAbilities.isInvulnerable());
				packet.b(serverPlayOutAbilities.isFlying());
				packet.c(serverPlayOutAbilities.CanFly());
				packet.d(serverPlayOutAbilities.CanInstantlyBuild());
			}
		}
		
	}

}
