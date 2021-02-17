node {
	    // reference to maven
	    // ** NOTE: This 'Maven3.6.3' Maven tool must be configured in the Jenkins Global Configuration.   
	    def mvnHome = tool 'Maven3.6.3'
	

	    // holds reference to docker image
	    def dockerImage
	    // ip address of the docker private repository(nexus)
	 
	    def dockerImageTag = "inventory-management${env.BUILD_NUMBER}"
	    
	    stage('Clone Repo') { // for display purposes
	      // Get some code from a GitHub repository
	      //git 'github.com:ksaurabh1028/msbatch3-inventory-management'
	      git (branch: 'main',
    		credentialsId: 'ass',
    		url: 'https://github.com/ksaurabh1028/msbatch3-inventory-management')
	      // Get the Maven tool.        
	      mvnHome = tool 'Maven3.6.3'
	    }  
	  
	    stage('Build Project') {
	      // build project via maven
	      sh "'${mvnHome}/bin/mvn' clean install"
	    }
			
	    stage('Build Docker Image') {
	      // build docker image
	      dockerImage = docker.build("inventory-management:${env.BUILD_NUMBER}")
	    }
	   
	    stage('Deploy Docker Image'){
	      
	      // deploy docker image to nexus
			
	      echo "Docker Image Tag Name: ${dockerImageTag}"
		  
		  sh "docker stop inventory-management"
		  
		  sh "docker rm inventory-management"
		  
		  sh "docker run --name inventory-management -d -p 8888:8888 inventory-management:${env.BUILD_NUMBER}"
		
	    }
	
	stage('Upload to Docker hub') {
		
		echo "Docker Image to be Uploaded"
		docker.withRegistry('https://registry.hub.docker.com', 'jenkins-docker') {
	            dockerImage.push("${env.BUILD_NUMBER}")
	      //      dockerImage.push("latest")
	        }
	}
   }
