const container = document.querySelector('.container');
const reset = document.querySelector('#reset');
const resize = document.querySelector('#resize');

var num = 16;

function createGrid(n) {
    container.style['grid-template'] = "repeat("+n+",1fr) / repeat("+n+",1fr)";
    for(n*=n;n > 0;n--) {
        var box = document.createElement('div');
        box.addEventListener('mouseenter', function(box) {
            box.target.style['background-color'] = 'black';
        });
        container.append(box);
    }
}
function deleteGrid() {
    while (container.firstChild) {
        container.firstChild.remove();
    }
}

reset.addEventListener('click', ()=>{
    deleteGrid();
    createGrid(num);
});

resize.addEventListener('click', ()=>{
   deleteGrid();
   num = prompt("Enter grid number: "); //Should check it..
   createGrid(num);
})

createGrid(num);