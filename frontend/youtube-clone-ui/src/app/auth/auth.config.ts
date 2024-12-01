import { PassedInitialConfig } from 'angular-auth-oidc-client';
import {environment} from '../../environment/environment';

export const authConfig: PassedInitialConfig = {
  config: {
            authority: environment.authConfig.authority,
            redirectUrl: typeof window!=='undefined'?window.location.origin : '',
            clientId: environment.authConfig.clientId,
            scope: 'openid profile offline_access',
            responseType: 'code',
            silentRenew: true,
            useRefreshToken: true,
        }
}
