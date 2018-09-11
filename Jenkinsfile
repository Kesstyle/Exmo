stage("Preparation") {
    node {
        git url: 'https://github.com/Kesstyle/Exmo.git', branch: BRANCH_NAME
        gradleHome = tool "Gradle 4"
    }
}

stage("Build") {
    node {
        bat("${gradleHome}/bin/gradle clean :exmo-api:jar :coinmarketcap-api:jar")
        archiveArtifacts artifacts: '**/build/libs/*-api*.jar'
    }
}

stage("Publish") {
    node {
        bat ("${gradleHome}/bin/gradle publish")
    }
}