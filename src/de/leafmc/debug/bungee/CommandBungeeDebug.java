package de.leafmc.debug.bungee;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public final class CommandBungeeDebug extends Command {

	public CommandBungeeDebug(final String name, final String permission, final String... aliases) {
		super(name, permission, aliases);
	}
	
	/**
	 * 
	 * <h2>execute(CommandSender, String[])</h2>
	 * 
	 * First of all, the method checks a few rules about the syntax.
	 * If the syntax isn't correct a proper syntax-message will show up and method stops prematurely.
	 * If the syntax is correct, the method checks per switch/case which length
	 * the args have and decides on that, if only a pluginrecord should show up the debugmessages
	 * or all pluginrecords.
	 * 
	 * At the next part, the method checks which length the given command has
	 * and depends on that, the plugin decides if all debugmessages will be shown or
	 * only on a specific "messagerecord".
	 * 
	 * @author PlugDev
	 * @version 1.0
	 * @since 2021-07-22
	 * 
	 * @param sender The commandSender, who's executing this command.
	 * @param args The given parameters by executing a command.
	 * 
	 */
	
	@SuppressWarnings("deprecation")
	@Override
	public void execute(final CommandSender sender, final String[] args) {
		if (args.length < 1 || args.length > 2) {
			sender.sendMessage(BungeeDebug.PREFIX + "�c/bdebug show [PluginName]");
			return;
		}
		if (!args[0].equalsIgnoreCase("show")) {
			sender.sendMessage(BungeeDebug.PREFIX + "�c/bdebug show [PluginName]");
			return;
		}
		
		switch (args.length) {
		case 1:
			for (final Object object : BungeeDebug.getInstance().getManager().getDebugs())
				sender.sendMessage("�c" + object.toString());
			break;
		case 2:
			if (!BungeeDebug.getInstance().getManager().isDebugged(args[1])) {
				sender.sendMessage("�cKeine Debugs ans Plugin �e" + args[1] + " �cvorhanden");
				return;
			}
			for (final Object object : BungeeDebug.getInstance().getManager().getDebugs(args[1]))
				sender.sendMessage(BungeeDebug.PREFIX + "�c" + object.toString());
			sender.sendMessage(BungeeDebug.PREFIX + "�cAnzahl der Debugs�7: �e"
					+ BungeeDebug.getInstance().getManager().getDebugs(args[1]).size());
			break;
		default:
			break;
		}
	}

}
