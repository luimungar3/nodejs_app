job('MiAplicacionNodeJSDocker') {
    description('aplicación Node.js con Docker')
    
    scm {
        git('https://github.com/luimungar3/nodejs_app', 'main') { node ->
            node / gitConfigName('Miguel')
            node / gitConfigEmail('correojenkins09@gmail.com')
        }
    }
    
    triggers {
        scm('H/5 * * * *') // Revisa cambios cada 5 minutos
    }
    
    wrappers {
        nodejs('NodeJS') 
    }
    
    steps {
        dockerBuildAndPublish {
            repositoryName('miguel7834/appnodejs')
            tag('${GIT_REVISION,length=7}')
            registryCredentials('docker-hub')
            forcePull(false)
            createFingerprints(false)
            skipDecorate()
        }
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
