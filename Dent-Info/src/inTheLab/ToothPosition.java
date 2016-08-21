package inTheLab;

public enum ToothPosition {
	UL1(11), UL2(12), UL3(13), UL4(14), UL5(15), UL6(16), UL7(17), UL8(18),
	UR1(21), UR2(22), UR3(23), UR4(24), UR5(25), UR6(26), UR7(27), UR8(28),
	DL1(31), DL2(32), DL3(33), DL4(34), DL5(35), DL6(36), DL7(37), DL8(38),
	DR1(41), DR2(42), DR3(43), DR4(44), DR5(45), DR6(46), DR7(47), DR8(48), UP(1), DOWN(2) ;
	private int value;

	private ToothPosition(int value) {
		this.setValue(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
