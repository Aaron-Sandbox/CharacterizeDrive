package frc.robot;

public class VoltageXPosition {

    private double voltage;
    private int position;

    public VoltageXPosition(double voltage, int position){
        this.voltage = voltage;
        this.position = position;
    }

    public double getVoltage(){
        return this.voltage;
    }

    public int getPosition(){
        return this.position;
    }

}