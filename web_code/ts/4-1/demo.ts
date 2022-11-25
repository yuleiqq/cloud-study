let a:object;
a={};
a=function(){

}

// {}

let b:{
    name : string 
}

// b={}
b={name : '123'}


//c 对象可能包含age 或者sex 属性 , ?表示属性可有可无
let c:{
    name : string ,
    age?:number,
    sex?:number
}
c={name:'sdf',age:20}




//需求二
