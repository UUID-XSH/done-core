package info.xsh.done.core.domain;

public enum YesOrNo {
    NO(0), YES(1);

    private int index;

    private YesOrNo(int i) {
        this.index = i;
    }

    public int Index() {
        return this.index;
    }
}
