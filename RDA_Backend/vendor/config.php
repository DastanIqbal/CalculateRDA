<?php

/**
 * Created by IntelliJ IDEA.
 * User: IQBAL-MEBELKART
 * Date: 8/23/2016
 * Time: 2:47 PM
 */


//Database Configuration
define('DB_HOST',"127.0.0.1");
define('DB_USER', "root");
define('DB_PASS',"");
define('DB_DATABASE',"ioh");


//BMR Calculation Parameters Value
define('BMR_MALE_OP1',66);
define('BMR_MALE_MULTIPLY_WEIGHT_OP2',13.7);
define('BMR_MALE_MULTIPLY_HEIGHT_OP3',5);
define('BMR_MALE_MULTIPLY_AGE_OP4',6.8);

define('BMR_FEMALE_OP1',655);
define('BMR_FEMALE_MULTIPLY_WEIGHT_OP2',9.6);
define('BMR_FEMALE_MULTIPLY_HEIGHT_OP3',1.8);
define('BMR_FEMALE_MULTIPLY_AGE_OP4',4.7);

//TCR Calculation Parameters Value
define('TCR_SEDENTARY',1.2);
define('TCR_LIGHTLY_ACTIVE',1.375);
define('TCR_MODERATELY_ACTIVE',1.55);
define('TCR_VERY_ACTIVE',1.725);
define('TCR_EXTRA_ACTIVE',1.9);