import { ResourceLanguage } from 'i18next';


const translations: ResourceLanguage = {
  main: {
    jumbotron: {
      titleText: 'Test your server\'s performance easily.',
      contentText: 'Input your web server\'s information, and run performance test.'
    }
  },
  input: {
    requester: {
      placeholder: 'Input your api to test.',
      results: {
        success: 'Success',
        warning: 'Warning',
        danger: 'Failed',
        none: 'Info'
      }
    }
  }
}


export default translations;