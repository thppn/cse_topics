const nums = document.querySelectorAll('.num');
const ops = document.querySelectorAll('.op');
const clear = document.querySelector('#func');
const equals = document.querySelector('#equals');
const textArea = document.querySelector('#text');

var lastIsOper = false;
operands = ['\*',"\/","\+","\-"];

nums.forEach((num) => {
    num.addEventListener('click',(e) => {
        textArea.innerHTML += e.target.id;
        isOper = false; 
    });
});

ops.forEach((op)=>{
    op.addEventListener('click',(e)=>{
        if(lastIsOper) {
            textArea.innerHTML = textArea.innerHTML.slice(0,-1);
        } else {
            lastIsOper = true;
        }
        textArea.innerHTML += e.target.innerHTML;
    });
});

equals.addEventListener('click',()=>{
    if(lastIsOper) return;
    buffer.push(number);
});

function result() {
    operands.forEach((str) => {                              
        while(buffer.indexOf(str) != -1) {                           
            
            
        }                                                               
    });
}

function operate(op, num1, num2){

    n = parseInt(num1);
    m = parseInt(num2);
    switch(str){
        case 'add': return (n+m).toString();
        case 'mul': return (n*m).toString();
        case 'sub': return (n-m).toString();
        case 'div': return (n/m).toString();
    }
}