"use strict";

// const xd = ()=>{
//   console.log(21222)
// }

// xd()

var xd = {
    name: "tom",
    func: function func() {
        var _this = this;

        setTimeout(function () {
            console.log(_this);
        }, 1000);
    }
};

xd.func();