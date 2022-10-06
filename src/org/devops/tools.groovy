package org.devops

// def PrintMes(content){
//     println(content)
// }
//格式化输出
def PrintMes(value,color){
    colors = ['red'   : "\033[40;31m >>>>>>>>>>>${value}<<<<<<<<<<< \033[0m",
              'blue'  : "\033[47;34m ${value} \033[0m",
              'green' : "[1;32m>>>>>>>>>>${value}>>>>>>>>>>[m",
              'green1' : "\033[40;32m >>>>>>>>>>>${value}<<<<<<<<<<< \033[0m" ]
    ansiColor('xterm') {
        println(colors[color])
    }
}



def exec(tool_package,command){
    home =tool "local_${tool_package}"
     
    try{
        if ("${tool_package}" == "maven"){
                sh "${home}/bin/mvn ${command}"
            }
        else{
            sh "${home}/bin/${tool_package} ${command}"
            }
        }
    catch(e){
        println(e)
    }
    
}
