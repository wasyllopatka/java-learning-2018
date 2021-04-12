<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Weather</title>
    <link href="webjars/bootstrap/4.1.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/weather.css"/>
</head>
<body style="">
<div class="row justify-content-center">
    <div class="col-6">
        <div class="card">
            <img src="${weather.image}" style="width: 40px; height: 40px"/>
            <div class="card-body">
                <div class="container">
                    <div class="row justify-content-start" id="container">
                        <div class="col-4" id="date">
                            <h4>${dateTime.day}</h4>
                            <h4>${dateTime.date}</h4>
                            <h4>${dateTime.time}</h4>
                        </div>
                        <div class="col-7" id="city">
                            <h1>${weather.plaats}</h1>
                        </div>
                    </div>
                    <div class="row justify-content-center">
                        <div class="col-3">
                            <h1>${weather.temp}&deg</h1>
                        </div>
                    </div>
                    <div class="row justify-content-center">
                        <div class="col-3">
                            <h6>${weather.bewolking}</h6>
                        </div>
                    </div>
                    <div class="row justify-content-start">
                        <div class="col-5">
                            <h6>Luchtdruk : ${weather.luchtdruk}</h6>
                            <h6>Windrichting : ${weather.windrichting}</h6>
                            <h6>Windsnelheid : ${weather.windsnelheid}</h6>
                        </div>
                    </div>
                </div>
            </div>
            <a href="/" class="btn btn-primary">Refresh</a>
        </div>
    </div>
</div>

</body>
</html>