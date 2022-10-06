@Library("jenkinslib") _ 
def tools = new org.devops.tools()

String workspace = "/opt/jenkins/workspace"


String command = "${env.command}"
String tool_package = "${env.tool_package}"
//pipline
pipeline{
// 	agent {node { label "master"
// 					customWorkspace "${workspace}"
// 		}
    agent any
    parameters { string(name: 'DEPLOY_ENV', defaultValue: 'staging', description: '') }
    // tools {
    //     maven 'apache-maven-3.0.1' 
    // }
// 	}
	options{
		timestamps() //日志的输出有时间
		skipDefaultCheckout() //删除隐式checkout scm语句（声明式默认会去下载代码）
		disableConcurrentBuilds() //禁止并行
		timeout(time: 1,unit: "HOURS") //流水线超时时间设置为1h
	}


	stages{
        stage('Parallel Stage') {
            failFast true
            parallel {
                		//下载代码
					stage("GetCode"){// 阶段名称
						steps{ //步骤
							timeout(time:5,unit:"MINUTES"){ //步骤超时时间
								script{ //填写运行代码
									// println("获取代码")
									// println("${test}")
									// println("${DEPLOY_ENV}")
									// mvnhome =tool "local_maven"
									// println("${mvnhome}")
									// sh "${mvnhome}/bin/mvn --version"
									tools.PrintMes("this is my lib","green")
								}

						}
					}
				}
					stage("Build"){
						steps{
							timeout(time:20,unit:"MINUTES"){ //步骤超时时间
								script{
									println("应用打包")
									// anthome =tool "local_ant"
									// println("${anthome}")
									// sh "${anthome}/bin/ant -version"
									tools.exec(tool_package,command)

								}
							}
						}
					}
			}   
		}
				stage("CodeScan"){
					//手动确认
					// input {
					//     message "Should we continue?"
					//     ok "Yes, we should."
					//     submitter "alice,bob"
					//     parameters {
					//         string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
					//     }
					// }
					when { environment name: 'test', value: 'abcc' }
					steps{ //步骤
						timeout(time:30,unit:"MINUTES"){ //步骤超时时间
							script{
								println("代码扫描")
							}

				}
			}
		}
	}
	post {
		always{
			script{
				println("always")
			}
		}
		success {
			script{
				currentBuild.description += "\n 构建成功"
			}
		}
		failure {
			script{
				currentBuild.description += "\n 构建失败"
			}
		}
		aborted {
			script{
				currentBuild.description += "\n 构建取消"
			}
		}
	}

}
