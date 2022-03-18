<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" style="height: 100%;">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    </head>
    <body style="height: 100%;">
        <div style="background-color: #8080809c;
        height: 100%; margin: 20px;
        ">
            <div style="text-align: center;">
                <img style="height: 100px;margin: 27px;
                border: 1px solid #0000ff6e;
                border-radius: 6px;" src='cid:logo' />
            </div>
            <div style="    width: 50%;            
            background-color: white;
            margin: 0 0 0 24%;
            border-radius: 3px;
            color: #000000c7;
            box-shadow: 0px 2px 11px 6px #547e97;
            padding: 20px;">
                <div style="text-align: center;">
                    <h1 style="font-family: sans-serif;text-align: center;">Hello ${name}!!</h1>
                </div>
                <hr style="width: 73%;
                height: 2px;
                background-color: #808080b8;
                margin: 29px;"/>
                <div style="font-family: sans-serif; text-align: center; font-size: 25px;text-align: center;">
                    Fare for your last ride from ${source} to ${destination} is ${fare} Euros.
					Your available balance is ${balance}
                </div>
                <div style="font-family: sans-serif;font-size: 20px;text-align: center;font-weight: bold;color: red;margin-top: 19px;">
                    ${commemntstart}Please Recharge Soon!!!!!${commemntend}
                </div> 
                <div style="margin: 33px;font-size: 20px;text-align: center;
                font-family: sans-serif; font-weight: bold;">
                    TEAM<span style="color: brown;"> EASY</span><span style="color: dodgerblue;">FARE</span> 
                </div>
            </div>
        </div>
    </body>
</html>