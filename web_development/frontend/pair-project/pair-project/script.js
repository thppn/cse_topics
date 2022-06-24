const buttonUpSession =  document.querySelector(".session_up");
const buttonDownSession =  document.querySelector(".session_down");

const buttonUpBreak =  document.querySelector(".break_up");
const buttonDownBreak =  document.querySelector(".break_down");

const timerText = document.querySelector(".session_text");
const breakTimerText = document.querySelector(".break_text");



const playButton =  document.querySelector(".play");
const pauseButton = document.querySelector(".pause");
const restartButton = document.querySelector(".restart");
const stopButton = document.querySelector(".stop");

var minutesSession = 25;
var secondsSession = 0;
var minutesSessionSaved = 0;
var secondsSessionSaved = 0;

var minutesBreak = 5;
var secondsBreak = 0;
var minutesBreakSaved = 0;
var secondsBreakSaved = 0;

function updateSessionDisplay() {
    timerText.innerHTML = minutesSession+":"+((secondsSession<9) ?"0":"")+secondsSession;
}
function updateBreakDisplay() {
    breakTimerText.innerHTML = minutesBreak+":"+((secondsBreak<9) ?"0":"")+secondsBreak;
}
function resetDisplay() {
    minutesSession = minSaved;
    secondsSession = 0;
    updateDisplay();
}

function counterSession() {
    if (minutesSession == 0 && secondsSession == 0){
        clearInterval(timer);
        minutesBreak = minutesBreakSaved;
        secondsBreak = secondsBreakSaved;
        break_timer = setInterval(counterBreak, 1000);
    }
    if(secondsSession == 0) {
        minutesSession -= 1;
        secondsSession = 60;
    }
    secondsSession -= 1;
    updateSessionDisplay();
}
function counterBreak() {
    if (minutesBreak == 0 && secondsBreak == 0){
        clearInterval(break_timer);
        minutesSession = minutesSessionSaved;
        secondsSession = secondsSessionSaved;
        timer = setInterval(counterSession, 1000);
    }
    if(secondsBreak == 0) {
        minutesBreak -= 1;
        secondsBreak = 60;
    }
    secondsBreak -= 1;
    updateBreakDisplay();
}


playButton.addEventListener('click', function() {
    minSaved = minutesSession;
    timer = setInterval(counterSession, 1000);
});

pauseButton.addEventListener('click', function() {
    clearInterval(timer);
});

stopButton.addEventListener('click', function() {
    clearInterval(timer);
    resetDisplay();
});

restartButton.addEventListener('click', function(){
    clearInterval(timer);
    minutesSession = 25;
    secondsSession = 0;
});


buttonUpSession.addEventListener('click', function(){
    minutesSession += 1;
    updateSessionDisplay();
});

buttonDownSession.addEventListener('click', function(){
    minutesSession -= 1;
    updateSessionDisplay();
});

buttonUpBreak.addEventListener('click', function(){
    minutesBreak += 1;
    updateBreakDisplay();
});

buttonDownBreak.addEventListener('click', function(){
    minutesBreak += 1;
    updateBreakDisplay();
});



