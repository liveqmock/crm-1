package com.deppon.crm.module.customer.shared.domain;

public class Test {
	private Integer a;
	private int b;
	private Boolean c;
	private boolean d;
	private Double e;
	private double f;
	public Integer getA() {
		return a;
	}
	public void setA(Integer a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public Boolean getC() {
		return c;
	}
	public void setC(Boolean c) {
		this.c = c;
	}
	public boolean isD() {
		return d;
	}
	public void setD(boolean d) {
		this.d = d;
	}
	public Double getE() {
		return e;
	}
	public void setE(Double e) {
		this.e = e;
	}
	public double getF() {
		return f;
	}
	public void setF(double f) {
		this.f = f;
	}
	public static void main(String[] args) {
		Test test = new Test();
		System.out.println(test);
		System.out.println(test.c.equals(null));
	}
	@Override
	public String toString() {
		return "Test [a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ", e="
				+ e + ", f=" + f + "]";
	}
}
