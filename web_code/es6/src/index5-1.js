
// const xd = ()=>{
//   console.log(21222)
// }
 
// xd()

const xd = {
    name: "tom",
    func(){
        setTimeout(()=>{
            console.log(this)
        },1000);
    },
};

xd.func() 