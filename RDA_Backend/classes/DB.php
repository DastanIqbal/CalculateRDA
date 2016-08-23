<?php

/**
 * Created by IntelliJ IDEA.
 * User: IQBAL-MEBELKART
 * Date: 8/23/2016
 * Time: 6:19 PM
 */

require '../vendor/config.php';

class DB
{

    private $dbConnection;

    public function __construct()
    {

        $this->dbConnection = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_DATABASE);
    }

    public function calculateRDA0($tcr)
    {
        $sql = 'SELECT t1.nutrients,t1.rda_lower,t1.rda_upper,t2.quantity_in_gms,t2.calories_in_kcal FROM rda_table_1 t1 INNER JOIN rda_table_2 t2 ON id_calories_a_gm=id_nutrients order by t1.id asc';
        $rs = $this->dbConnection->query($sql);
        $arr = $rs->fetch_all(MYSQLI_ASSOC);
        $result=array();
        foreach ($arr as $value) {
            $value['rda_lower'] = round($tcr * ($value['rda_lower'] / 100),2);
            $value['rda_upper'] = round($tcr * ($value['rda_upper'] / 100),2);
            $value['lower_calories_in_gram'] = round(($value['rda_lower'] * $value['quantity_in_gms']) / $value['calories_in_kcal'],2); // calories in grams (lower/pergramkcal) grams
            $value['upper_calories_in_gram'] = round(($value['rda_upper'] * $value['quantity_in_gms']) / $value['calories_in_kcal'],2);
            $result[]=$value;
        }

        return $result;
    }

    public function calculateRDA1()
    {
        $sql = 'SELECT t1.nutrients,t1.rda_lower,t1.rda_upper,t2.quantity_in_gms,t2.calories_in_kcal, FROM rda_table_1 t1 INNER JOIN rda_table_2 t2 ON id_calories_a_gm=id_nutrients order by t1.id asc';
        $rs = $this->dbConnection->query($sql);
        $arr = $rs->fetch_all(MYSQLI_ASSOC);
    }

    public function calculateRDA2()
    {
        $sql = 'SELECT nutrients,quantity_in_gms,calories_in_kcal FROM rda_table_2 order by id asc';
        $rs = $this->dbConnection->query($sql);
        $arr = $rs->fetch_all(MYSQLI_ASSOC);
    }
}