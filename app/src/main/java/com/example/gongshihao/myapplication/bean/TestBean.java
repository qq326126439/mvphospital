package com.example.gongshihao.myapplication.bean;

/**
 * Created by gongshihao on 2018/4/14.
 */

public class TestBean {

    /**
     * formula : {"mp":10,"ammo":10,"mre":10,"part":10,"input_level":0}
     * dev_type : 0
     * count : 247555
     * total : 4624080
     */

    private FormulaBean formula;
    private int dev_type;
    private int count;
    private int total;

    public FormulaBean getFormula() {
        return formula;
    }

    public void setFormula(FormulaBean formula) {
        this.formula = formula;
    }

    public int getDev_type() {
        return dev_type;
    }

    public void setDev_type(int dev_type) {
        this.dev_type = dev_type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static class FormulaBean {
        /**
         * mp : 10
         * ammo : 10
         * mre : 10
         * part : 10
         * input_level : 0.0
         */

        private int mp;
        private int ammo;
        private int mre;
        private int part;
        private double input_level;

        public int getMp() {
            return mp;
        }

        public void setMp(int mp) {
            this.mp = mp;
        }

        public int getAmmo() {
            return ammo;
        }

        public void setAmmo(int ammo) {
            this.ammo = ammo;
        }

        public int getMre() {
            return mre;
        }

        public void setMre(int mre) {
            this.mre = mre;
        }

        public int getPart() {
            return part;
        }

        public void setPart(int part) {
            this.part = part;
        }

        public double getInput_level() {
            return input_level;
        }

        public void setInput_level(double input_level) {
            this.input_level = input_level;
        }
    }
}
