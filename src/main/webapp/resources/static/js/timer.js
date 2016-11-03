    function rentTimer(data){

                var startTime = new Date();
                var serverSeconds = parseInt(data);
                if(document.getElementById("timer") != null){
                var secInterval = setInterval(startTimer, 1000);
                document.getElementById("timer").innerHTML = calculateTime(serverSeconds);
                function startTimer(){
                        var currentTime = new Date();
                        var difference = parseInt((currentTime.getTime() - startTime.getTime())/1000);
                        var secondsPassed = serverSeconds + difference;
                        document.getElementById("timer").innerHTML = calculateTime(secondsPassed);
                    }
                }

                function normalize(i) {
                    if (i < 10) {
                    i = "0" + i
                    }
                    return i;
                }

                function calculateTime(sec){
                        var hours = parseInt(sec/3600);
                        var minutes = parseInt(sec/60);
                        var seconds = sec%60;

                        hours = normalize(hours);
                        minutes = normalize(minutes);
                        seconds = normalize(seconds);

                        return hours + " : " + minutes + " : " + seconds;
                }
    }