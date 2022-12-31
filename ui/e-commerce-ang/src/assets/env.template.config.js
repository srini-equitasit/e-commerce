// setting environment variables
// export authDomain=dev-gh3wgihw.jp.auth0.com
// export clientId=zqLb5a5bYoLPZoU71JAvigGh5N7TmtsZ

// below values will be replaced with envsubst
 // envsubst < env.template.config.js > test.js

sessionStorage.setItem('authDomain','$authDomain');
sessionStorage.setItem('clientId','$clientId');
