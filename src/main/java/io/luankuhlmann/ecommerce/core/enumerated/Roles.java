package io.luankuhlmann.ecommerce.core.enumerated;

public enum Roles {
    ADMIN(1),
    USER(2);

    private final int order;

    Roles(int order) {
        this.order = order;
    }
}
