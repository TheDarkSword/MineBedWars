package it.mineblock.bedwars.enums;

public enum Permissions {
    FULL("*"),
    EDIT("edit"),
    RELOAD("reload"),
    STAFF("staff");

    private String perm;

    Permissions(String perm) {
        this.perm = perm;
    }

    public String get() {
        return "mbw." + perm;
    }
}
