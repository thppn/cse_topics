var player, computer, end, needSetup=true;

function computerPlay() {
  switch(Math.floor((Math.random()*2))) {
    case 0:
      return 'rock';
    case 1:
      return 'paper';
    case 2:
      return 'scissor';
  }
}
function playRound(player, computer) {
  if(player == computer) return 0;
  else if(player == 'rock') {
    if(computer == 'scissor') return 1;
    else return -1;}
  else if(player == 'scissor') {
    if(computer == 'paper') return 1;
    else return -1;}
  else if(player == 'paper') {
    if(computer == 'rock') return 1;
    else return -1;}
  else
    return null;
}
function setup() {
  player=0;
  computer=0;
  end = 3;
  needSetup=false;
}
function game(playerPlay) {
  if(needSetup) setup();
  score = playRound(playerPlay, computerPlay());
  result="Draw";
  switch(score) {
    case 1:
      player++;
      result="Win";
      break;
    case -1:
      computer++;
      result="Defeat";
      break;    
  }
  var area = document.querySelector(".area")
  area.textContent=`${player} - ${computer}`;
  var message = document.querySelector(".message");
  message.textContent = result;
  if(player<end && computer<end) return;
  var img = document.querySelector('.result');
  var imgSrc = 'images/'+(player>computer?'win.png':'defeat.png');
  img.setAttribute('src', imgSrc);
  img.hidden=false;
}
const buttons = document.querySelectorAll('.button');
buttons.forEach((button) => {
  button.addEventListener('click', (e) => {
    game(button.id);
  });
});