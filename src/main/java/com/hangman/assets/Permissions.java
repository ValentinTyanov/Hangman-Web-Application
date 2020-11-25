package com.hangman.assets;

import java.util.HashSet;
import java.util.Set;

public final class Permissions {
  public static Set<String> getPermissions(String role) {
    Set<String> permissions = new HashSet<>();
    if (role.equals("admin")) {
      permissions.add("*");
    } else if (role.equals("analyst")) {
      permissions.add("games:read");
    }
    return permissions;
  }
}
