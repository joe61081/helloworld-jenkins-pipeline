pipeline{
	options {
		buildDiscarder(logRotator(numToKeepStr: '2', artifactNumToKeepStr: '2'))
	}
	triggers {
        githubPush()
    }
	agent any
	stages{
		stage('master'){
			steps{
				scm {
					git(url: 'git@github.com:joe61081/helloworld-jenkins-pipeline.git',credentialsId:'joe-github-ssh-token')
    			}
				withMaven(
						// Maven installation declared in the Jenkins "Global Tool Configuration"
						maven: 'maven-3.6.1',
						// Maven settings.xml file defined with the Jenkins Config File Provider Plugin
						// We recommend to define Maven settings.xml globally at the folder level using
						// navigating to the folder configuration in the section "Pipeline Maven Configuration / Override global Maven configuration"
						// or globally to the entire master navigating to  "Manage Jenkins / Global Tools Configuration"
						mavenSettingsConfig: 'pom.xml') {

					// Run the maven build
					sh "mvn clean verify"

				}
				runmvn();
			}

		}
		stage('feature'){
			when{
				branch "feature*"
			}
			steps{
				runmvn();
			}

		}
		stage('DEV'){
			when{
				tag "dev-*"
			}
			steps{
				runmvn();
			}
		}
		stage('SIT'){
			when{
				tag "sit-*"
			}
			steps{
				runmvn();
			}

		}
		stage('UAT'){
			when{
				tag "uat-*"
			}
			steps{
				runmvn();
			}

		}
		stage('PRE'){
			when{
				tag "pre-*"
			}
			steps{
				runmvn();
			}

		}
		stage('PRD'){
			when{
				tag "prd-*"
			}
			steps{
				runmvn();
			}

		}

	}
}

void runmvn(){
	readMavenPom file: 'pom.xml'
	sh 'mvn install'
	sh 'mvn run'
}
