
//泛型
function cache<T> (prop:T):T{

    return prop;
}

//指定泛型的类型为string
let a = cache<string>("true");
