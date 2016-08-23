<?php
use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

require '../vendor/autoload.php';


$app = new \Slim\App();

$app->post('/api/getRDA', function (Request $request, Response $response) {
    $json  = $request->getParsedBody();
    if($json!=null && $json['json']!=null) {
        $req=json_decode($json['json'],true);
        $db = new DB();
        $bmr = calculateBMR($req['weight_in_kg'], $req['height_in_cm'], $req['age'], $req['gender']);
        if ($bmr <= 0) {
            $result = array("status" => 0, "msg" => "Not enough data to calculate RDA");
            $response->getBody()->write(json_encode($result));
            return $response;
        }
        $tcr = calculateTCR($bmr, $req['activity']);//json_decode($bmr, true)['BMR'], 0);
        if ($tcr <= 0) {
            $result = array("status" => 0, "msg" => "Not enough data to calculate RDA");
            $response->getBody()->write(json_encode($result));
            return $response;
        }
        $caloriesIngram = $db->calculateRDA0($tcr);//json_decode($tcr, true)['TCR']);
        if (count($caloriesIngram) <= 0) {
            $result = array("status" => 0, "msg" => "Not enough data to calculate RDA");
            $response->getBody()->write(json_encode($result));
            return $response;
        }
        $result = array("status" => 1, "BMR" => $bmr, "TCR" => $tcr, "data" => $caloriesIngram);
        $response->getBody()->write(json_encode($result));
    }else{

    }
    return $response;
});

$app->run();

/**
 * @param $weightInKg
 * @param $heightInCm
 * @param $age
 * @param $gender 0 for female 1 for male
 * @return string
 */
function calculateBMR($weightInKg, $heightInCm, $age, $gender)
{
    //$gender 0 for Female, 1 for Male
    if ($gender == 0) { //Female

        $bmr = BMR_FEMALE_OP1 + (BMR_FEMALE_MULTIPLY_WEIGHT_OP2 * $weightInKg) +
            (BMR_FEMALE_MULTIPLY_HEIGHT_OP3 * $heightInCm) - (BMR_FEMALE_MULTIPLY_AGE_OP4 * $age);

//        $result = array("status" => 1, "BMR" => $bmr);
//        return json_encode($result);

    } else /*if ($gender == 1)*/ { //Male
        $bmr = BMR_MALE_OP1 + (BMR_MALE_MULTIPLY_WEIGHT_OP2 * $weightInKg) +
            (BMR_MALE_MULTIPLY_HEIGHT_OP3 * $heightInCm) - (BMR_MALE_MULTIPLY_AGE_OP4 * $age);

//        $result = array("status" => 1, "BMR" => $bmr);
//        return json_encode($result);

//    } else {
//        $error = array("status" => 0, "msg" => "No Gender Found");
//        return json_encode($error);
//    }
    }

    return $bmr;
}

/**
 * @param $bmr
 * @param $activityFactor 0 Sedentary, 1 lightly,moderately,very,extra active
 * @return string
 */
function calculateTCR($bmr, $activityFactor)
{
    if ($activityFactor == 0) { //Sedentary

        $calc = TCR_SEDENTARY * $bmr;
//        $result = array("status" => 1, "TCR" => $calc);
//        return json_encode($result);

    } else if ($activityFactor == 2) { //Lightly Active

        $calc = TCR_LIGHTLY_ACTIVE * $bmr;
//        $result = array("status" => 1, "TCR" => $calc);
//        return json_encode($result);

    } else if ($activityFactor == 3) { //Moderately Active

        $calc = TCR_MODERATELY_ACTIVE * $bmr;
//        $result = array("status" => 1, "TCR" => $calc);
//        return json_encode($result);

    } else if ($activityFactor == 4) { //Very Active

        $calc = TCR_VERY_ACTIVE * $bmr;
//        $result = array("status" => 1, "TCR" => $calc);
//        return json_encode($result);

    } else if ($activityFactor == 5) { //Extra Active

        $calc = TCR_EXTRA_ACTIVE * $bmr;
//        $result = array("status" => 1, "TCR" => $calc);
//        return json_encode($result);

    } else {
//        $error = array("status" => 0, "msg" => "No Gender Found");
//        return json_encode($error);
        $calc = 0;
    }
    return $calc;
}
