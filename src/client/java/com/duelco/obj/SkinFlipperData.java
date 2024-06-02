package com.duelco.obj;

public class SkinFlipperData {
    private boolean isSkinFlipped;
    private String changingSkin;
    private String constantSkin;

    public SkinFlipperData() {
        this.isSkinFlipped = false;
        this.changingSkin = "skin1";
        this.constantSkin = "skin2";
    }

    public boolean isSkinFlipped() {
        return this.isSkinFlipped;
    }

    public void setSkinFlipped(boolean skinFlipped) {
        this.isSkinFlipped = skinFlipped;
    }

    public String getChangingSkin() {
        return this.changingSkin;
    }

    public String getConstantSkin() {
        return this.constantSkin;
    }

    public String flipSkin() {
        String postFlipSkin = this.isSkinFlipped ? this.getChangingSkin() : this.getConstantSkin();

        this.setSkinFlipped(!this.isSkinFlipped);
        return postFlipSkin;
    }

    public void setChangingSkin(String updatedSkin) {
        this.changingSkin = updatedSkin;
    }
}
