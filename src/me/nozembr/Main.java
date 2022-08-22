//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.nozembr;

import org.bukkit.*;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.security.Permission;
import java.util.ArrayList;

public class Main extends JavaPlugin implements Listener {
    public static ArrayList<String> teleportlava = new ArrayList();
    public static Permission perms = null;

    public Main() {
    }

    public void onEnable() {
        PluginDescriptionFile pdfFile = this.getDescription();
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "[LavaN]" + ChatColor.GREEN + " Enable," + ChatColor.GREEN + " Version: " + pdfFile.getVersion());
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "[LavaN]" + ChatColor.RED + " Disable");
    }

    @EventHandler
    public void TeleportLava(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (teleportlava.contains(p.getName())) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void PlacaSopa(SignChangeEvent e) {
        if (e.getLine(0).equalsIgnoreCase("[LavaSoup]")) {
            e.setLine(0, ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("Line1")));
            e.setLine(1, ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("Line2")));
            e.setLine(2, ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("Line3")));
            e.setLine(3, ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("Line4")));
        }

    }

    @EventHandler
    public void AbrirPlaca(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getClickedBlock().getState() instanceof Sign) {
                Sign s = (Sign) e.getClickedBlock().getState();
                Inventory soups = Bukkit.createInventory((InventoryHolder) null, 36, ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("NameMenu")));
                if (s.getLine(0).equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("Line1")))) {
                    for (int i = 0; i < 36; ++i) {
                        ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
                        ItemMeta soupmeta = soup.getItemMeta();
                        soupmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("NameSoup")));
                        soup.setItemMeta(soupmeta);
                        soups.setItem(i, soup);
                    }

                    p.openInventory(soups);
                }
            }

        }
    }

    public boolean onCommand(final CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && !(sender instanceof ConsoleCommandSender)) {
            final Player p = (Player) sender;
            if (command.getName().equalsIgnoreCase("lava")) {
                if (args.length == 0) {
                    sender.sendMessage("§b------- LavaN Plugin ---------");
                    sender.sendMessage(" ");
                    sender.sendMessage("§b- /leave: §aExit from LavaChallange.");
                    sender.sendMessage(" ");
                    sender.sendMessage("- §b/lava easy: §aEnter in easy mode.");
                    sender.sendMessage(" ");
                    sender.sendMessage("- §b/lava medium: §aEnter in medium mode.");
                    sender.sendMessage(" ");
                    sender.sendMessage("- §b/lava hard: §aEnter in hard mode.");
                    sender.sendMessage(" ");
                    sender.sendMessage("§b- /lvadmin: §cSee admin commands.");
                    sender.sendMessage(" ");
                    sender.sendMessage("§b------------------------------");
                }else {
                        BukkitScheduler scheduler;
                        if (args[0].equalsIgnoreCase("easy")) {
                            if (this.getConfig().contains("easy")) {
                                teleportlava.add(p.getName());
                                scheduler = Bukkit.getServer().getScheduler();
                                scheduler.scheduleSyncDelayedTask(this, new Runnable() {
                                    public void run() {
                                        Player p1 = (Player) sender;
                                        World w = Bukkit.getServer().getWorld(Main.this.getConfig().getString("easy.world"));
                                        double x = Main.this.getConfig().getDouble("easy.x");
                                        double y = Main.this.getConfig().getDouble("easy.y");
                                        double z = Main.this.getConfig().getDouble("easy.z");
                                        Location lobby = new Location(w, x, y, z);
                                        lobby.setPitch((float) Main.this.getConfig().getDouble("easy.pitch"));
                                        lobby.setYaw((float) Main.this.getConfig().getDouble("easy.yaw"));
                                        p1.teleport(lobby);
                                        Main.teleportlava.remove(p.getName());
                                        p1.sendMessage(ChatColor.AQUA + "Teleported to LavaChallange : " + ChatColor.GREEN + "easy");
                                        ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
                                        ItemMeta sopameta = sopa.getItemMeta();
                                        sopameta.setDisplayName(ChatColor.GREEN + "Soup");
                                        sopa.setItemMeta(sopameta);
                                        ItemStack helmet = new ItemStack(Material.AIR);
                                        ItemStack armor = new ItemStack(Material.AIR);
                                        ItemStack legs = new ItemStack(Material.AIR);
                                        ItemStack boots = new ItemStack(Material.AIR);
                                        new ItemStack(Material.AIR);
                                        p.getEquipment().setHelmet(helmet);
                                        p.getEquipment().setChestplate(armor);
                                        p.getEquipment().setLeggings(legs);
                                        p.getEquipment().setBoots(boots);
                                        p.getInventory().clear();

                                        for (int i = 1; i < 37; ++i) {
                                            p.getInventory().addItem(new ItemStack[]{sopa});
                                        }

                                    }
                                });
                            } else {
                                p.sendMessage(ChatColor.RED + "Lava Challenge easy can't be found");
                            }
                        } else if (args[0].equalsIgnoreCase("medium")) {
                            if (this.getConfig().contains("medium")) {
                                teleportlava.add(p.getName());
                                scheduler = Bukkit.getServer().getScheduler();
                                scheduler.scheduleSyncDelayedTask(this, new Runnable() {
                                    public void run() {
                                        Player p1 = (Player) sender;
                                        World w = Bukkit.getServer().getWorld(Main.this.getConfig().getString("medium.world"));
                                        double x = Main.this.getConfig().getDouble("medium.x");
                                        double y = Main.this.getConfig().getDouble("medium.y");
                                        double z = Main.this.getConfig().getDouble("medium.z");
                                        Location lobby = new Location(w, x, y, z);
                                        lobby.setPitch((float) Main.this.getConfig().getDouble("medium.pitch"));
                                        lobby.setYaw((float) Main.this.getConfig().getDouble("medium.yaw"));
                                        p1.teleport(lobby);
                                        Main.teleportlava.remove(p.getName());
                                        p1.sendMessage(ChatColor.AQUA + "Teleported to LavaChallange : " + ChatColor.YELLOW + "medium");
                                        ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
                                        ItemMeta sopameta = sopa.getItemMeta();
                                        sopameta.setDisplayName(ChatColor.GREEN + "Soup");
                                        sopa.setItemMeta(sopameta);
                                        ItemStack helmet = new ItemStack(Material.AIR);
                                        ItemStack armor = new ItemStack(Material.AIR);
                                        ItemStack legs = new ItemStack(Material.AIR);
                                        ItemStack boots = new ItemStack(Material.AIR);
                                        new ItemStack(Material.AIR);
                                        p.getEquipment().setHelmet(helmet);
                                        p.getEquipment().setChestplate(armor);
                                        p.getEquipment().setLeggings(legs);
                                        p.getEquipment().setBoots(boots);
                                        p.getInventory().clear();

                                        for (int i = 1; i < 37; ++i) {
                                            p.getInventory().addItem(new ItemStack[]{sopa});
                                        }

                                    }
                                });
                            } else {
                                p.sendMessage(ChatColor.RED + "Lava Challenge medium can't be found");
                            }
                        } else if (args[0].equalsIgnoreCase("hard")) {
                            if (this.getConfig().contains("hard")) {
                                teleportlava.add(p.getName());
                                scheduler = Bukkit.getServer().getScheduler();
                                scheduler.scheduleSyncDelayedTask(this, new Runnable() {
                                    public void run() {
                                        Player p1 = (Player) sender;
                                        World w = Bukkit.getServer().getWorld(Main.this.getConfig().getString("hard.world"));
                                        double x = Main.this.getConfig().getDouble("hard.x");
                                        double y = Main.this.getConfig().getDouble("hard.y");
                                        double z = Main.this.getConfig().getDouble("hard.z");
                                        Location lobby = new Location(w, x, y, z);
                                        lobby.setPitch((float) Main.this.getConfig().getDouble("hard.pitch"));
                                        lobby.setYaw((float) Main.this.getConfig().getDouble("hard.yaw"));
                                        p1.teleport(lobby);
                                        Main.teleportlava.remove(p.getName());
                                        p1.sendMessage(ChatColor.AQUA + "Teleported to LavaChallange : " + ChatColor.RED + "hard");
                                        ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
                                        ItemMeta sopameta = sopa.getItemMeta();
                                        sopameta.setDisplayName(ChatColor.GREEN + "Soup");
                                        sopa.setItemMeta(sopameta);
                                        ItemStack helmet = new ItemStack(Material.AIR);
                                        ItemStack armor = new ItemStack(Material.AIR);
                                        ItemStack legs = new ItemStack(Material.AIR);
                                        ItemStack boots = new ItemStack(Material.AIR);
                                        new ItemStack(Material.AIR);
                                        p.getEquipment().setHelmet(helmet);
                                        p.getEquipment().setChestplate(armor);
                                        p.getEquipment().setLeggings(legs);
                                        p.getEquipment().setBoots(boots);
                                        p.getInventory().clear();

                                        for (int i = 1; i < 37; ++i) {
                                            p.getInventory().addItem(new ItemStack[]{sopa});
                                        }

                                    }
                                });
                            } else {
                                p.sendMessage(ChatColor.RED + "Lava Challenge hard can't be found");
                            }
                        } else if (args[0].equalsIgnoreCase("reload")) {
                            if (p.hasPermission("lavan.reload")) {
                                this.reloadConfig();
                                p.sendMessage(ChatColor.GREEN + "Config reloaded !");
                            } else {
                                p.sendMessage(ChatColor.RED + "You don't have permission");
                            }
                        }
                    }

                    return true;

                }
                BukkitScheduler scheduler;
                if (command.getName().equalsIgnoreCase("leave")) {
                    if (this.getConfig().contains("leave")) {
                        teleportlava.add(p.getName());
                        scheduler = Bukkit.getServer().getScheduler();
                        scheduler.scheduleSyncDelayedTask(this, new Runnable() {
                            public void run() {
                                Player p1 = (Player) sender;
                                World w = Bukkit.getServer().getWorld(Main.this.getConfig().getString("leave.world"));
                                double x = Main.this.getConfig().getDouble("leave.x");
                                double y = Main.this.getConfig().getDouble("leave.y");
                                double z = Main.this.getConfig().getDouble("leave.z");
                                Location lobby = new Location(w, x, y, z);
                                lobby.setPitch((float) Main.this.getConfig().getDouble("leave.pitch"));
                                lobby.setYaw((float) Main.this.getConfig().getDouble("leave.yaw"));
                                p1.teleport(lobby);
                                Main.teleportlava.remove(p.getName());
                                p1.sendMessage(ChatColor.AQUA + "You left from LavaChallenge");
                                p.getInventory().clear();


                            }
                        });
                    } else {
                        p.sendMessage(ChatColor.RED + "Exit has not been set");
                    }}
                else if (command.getName().equalsIgnoreCase("lvadmin")) {
                    if (p.hasPermission("lavan.set")) {
                        if (args.length == 0) {
                            p.getPlayer().sendMessage("§c------- LavaN Admin -----------");
                            p.getPlayer().sendMessage(" ");
                            p.getPlayer().sendMessage("§b- /lavaset leave: §cSet exit from LavaChallange.");
                            p.getPlayer().sendMessage(" ");
                            p.getPlayer().sendMessage("§b- /lavaset easy: §cSet easy mode.");
                            p.getPlayer().sendMessage(" ");
                            p.getPlayer().sendMessage("§b- /lavaset medium: §cSet medium mode.");
                            p.getPlayer().sendMessage(" ");
                            p.getPlayer().sendMessage("§b- /lavaset hard: §cSet hard mode.");
                            p.getPlayer().sendMessage(" ");
                            p.getPlayer().sendMessage("§b- /lava reload: §cReload config.");
                            p.getPlayer().sendMessage(" ");
                            p.getPlayer().sendMessage("§c------------------------------");
                        }
                    }else {
                        p.sendMessage(ChatColor.RED + "You don't have permission");
                    }

                } else if (command.getName().equalsIgnoreCase("lavaset")) {
                    if (p.hasPermission("lavan.set")) {
                        if (args.length == 0) {
                            p.getPlayer().sendMessage(ChatColor.RED + "Use : easy, medium, hard");
                        } else if (args[0].equalsIgnoreCase("easy")) {
                            this.getConfig().set("easy.x", p.getLocation().getX());
                            this.getConfig().set("easy.y", p.getLocation().getY());
                            this.getConfig().set("easy.z", p.getLocation().getZ());
                            this.getConfig().set("easy.pitch", p.getLocation().getPitch());
                            this.getConfig().set("easy.yaw", p.getLocation().getYaw());
                            this.getConfig().set("easy.world", p.getLocation().getWorld().getName());
                            this.saveConfig();
                            p.sendMessage(ChatColor.RED + "Lava Challenge EASY mode has been set.");
                        } else if (args[0].equalsIgnoreCase("medium")) {
                            this.getConfig().set("medium.x", p.getLocation().getX());
                            this.getConfig().set("medium.y", p.getLocation().getY());
                            this.getConfig().set("medium.z", p.getLocation().getZ());
                            this.getConfig().set("medium.pitch", p.getLocation().getPitch());
                            this.getConfig().set("medium.yaw", p.getLocation().getYaw());
                            this.getConfig().set("medium.world", p.getLocation().getWorld().getName());
                            this.saveConfig();
                            p.sendMessage(ChatColor.RED + "Lava Challenge MEDIUM mode has been set");
                        } else if (args[0].equalsIgnoreCase("hard")) {
                            this.getConfig().set("hard.x", p.getLocation().getX());
                            this.getConfig().set("hard.y", p.getLocation().getY());
                            this.getConfig().set("hard.z", p.getLocation().getZ());
                            this.getConfig().set("hard.pitch", p.getLocation().getPitch());
                            this.getConfig().set("hard.yaw", p.getLocation().getYaw());
                            this.getConfig().set("hard.world", p.getLocation().getWorld().getName());
                            this.saveConfig();
                            p.sendMessage(ChatColor.RED + "Lava Challenge HARD mode has been set.");
                        } else if (args[0].equalsIgnoreCase("leave")) {
                            this.getConfig().set("leave.x", p.getLocation().getX());
                            this.getConfig().set("leave.y", p.getLocation().getY());
                            this.getConfig().set("leave.z", p.getLocation().getZ());
                            this.getConfig().set("leave.pitch", p.getLocation().getPitch());
                            this.getConfig().set("leave.yaw", p.getLocation().getYaw());
                            this.getConfig().set("leave.world", p.getLocation().getWorld().getName());
                            this.saveConfig();
                            p.sendMessage(ChatColor.RED + "Exit has been set");
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "You don't have permission");
                    }

                    return true;
                } else {
                    return false;
                }
            } else {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Command Only in Game.");
                return true;
            }
        return true;
        }
    }
