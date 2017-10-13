package net.ttk1;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class InfiniteLava extends JavaPlugin {
    @Override
    public void onEnable() {
    	//ログインメッセージの表示設定
    	getServer().getPluginManager().registerEvents(new LoginMsg(), this);
    	//開始時のログ出力
        getLogger().info("InfiniteLava enabled");
    }

    @Override
    public void onDisable() {
    	//停止時のログ出力
        getLogger().info("InfiniteLava disabled");
    }
    
    class LoginMsg implements Listener {
    	@EventHandler
        public void onBlockPhysics(BlockPhysicsEvent event) {

        }
    }
}
