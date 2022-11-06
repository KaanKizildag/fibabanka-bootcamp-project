package tr.fibabanka.dto.enums;

public enum CartStatus {

    YENI(0),
    BITMIS(1);
    public final int value;

    private CartStatus(int value) {
        this.value = value;
    }
}
