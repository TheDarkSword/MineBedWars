package it.mineblock.bedwars.enums;

import it.mineblock.bedwars.Main;
import it.mineblock.mbcore.spigot.Chat;

public enum Messages {
    NOT_PLAYER("not-player"),
    CLICK_TO_JOIN("click-to-join"),
    MAP_CONFIGURATION("map-configuration"),
    YOU_CANT_DO_THIS("you-cant-do-this"),
    EXCEPTION("exception"),
    INCORRECT_USAGE("incorrect-usage"),
    MAP_CREATED("map-created"),
    EXSISTING_MAP("exsisting-map"),
    DELETE_ENTITY("delete-entity"),
    NOT_EXISTING_MAP("not-existing-map"),
    GLASS_NOT_FOUND("glass-not-found"),
    INCORRECT_WORLD("incorrect-world"),
    INCORRECT_ENTITYTYPE("incorrect-entitytype"),
    INCORRECT_TEAM("incorrect-team"),
    SET_TEAM_SPAWN("set-team-spawn"),
    SET_TEAM_ADD("set-team-add"),
    SET_TEAM_REMOVE("set-team-remove"),
    SET_RADIUS("set-radius"),
    SHOP_SPAWN("shop-spawn"),
    TEAM_FULL("team-full"),
    TEAM_JOIN("team-join"),
    NEAR_MAP_LIMIT("near-map-limit"),
    TIMER_START_BEDWARS("timer-start-bedwars"),
    NOT_BED_BLOCK("not-bed-block"),
    DESTROY_YOUR_BED("destroy-your-bed"),
    INSUFFICIENT_PERM("insufficient-perm"),
    INVALID_NUMBER("invalid-number"),
    RESOURCES_SET("resources-set"),
    BED_NOT_FOUND("bed-not-found"),
    BED_SET("bed-set"),
    GAME_STARTED_KICK("game-started-kick"),
    SHOUT_FORMAT("shout-format"),
    SHOUT_TIMEOUT("shout-timeout"),
    DEATH_TITLE("death-title"),
    DEATH_SUBTITLE("death-subtitle"),
    INCORRECT_ARGUMENT(""),
    TEAM_SELECTOR(""),
    TEAM_NAME(""),
    NOT_NOW("");

    private String message;

    Messages(String message) {
        this.message = "messages." + message;
    }

    public String get() {
        return Chat.getTranslated(Main.messages.getString(message));
    }

    public String getReplaced(String target, String replacement) {
        return Chat.getTranslated(Main.messages.getString(message).replace(target, replacement));
    }

    public String getReplaced(String[] target, String[] replacement) {
        String msg = Main.messages.getString(message);

        for (int i = 0; i < target.length; i++) {
            msg = msg.replace(target[i], replacement[i]);
        }

        return msg;
    }
}
