job('MiAplicacionNodeJS') {
    description('aplicación Node.js usando DSL')
    
    scm {
        git('https://github.com/luimungar3/nodejs_app', 'main') { node ->
            node / gitConfigName('luimungar3')
            node / gitConfigEmail('correojenkins09@gmail.com')
        }
    }
    
    triggers {
        scm('H/5 * * * *') 
    }
    
    wrappers {
        nodejs('NodeJS') 
    }
    
    steps {
        shell("npm install") 
        shell("npm start")   
    }
    
    publishers {
        slackNotifier {
            notifyAborted(true)
            notifyEveryFailure(true)
            notifyNotBuilt(false)
            notifyUnstable(false)
            notifyBackToNormal(true)
            notifySuccess(true)
            notifyRepeatedFailure(false)
            startNotification(false)
            includeTestSummary(false)
            includeCustomMessage(false)
            customMessage(null)
            sendAs(null)
            commitInfoChoice('NONE')
            teamDomain(null)
            authToken(null)
        }
    }
}
