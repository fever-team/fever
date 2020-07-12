import React from 'react';
import { BrowserRouter, Route, Switch, Redirect } from 'react-router-dom';
import { withTranslation, WithTranslation } from 'react-i18next';

// Import blueprint css files
import "normalize.css";
import "@blueprintjs/core/lib/css/blueprint.css";
import "@blueprintjs/icons/lib/css/blueprint-icons.css";


import routes from '../routes';


export interface Props extends WithTranslation { } {};

const App: React.FC<Props> = ({ t, i18n, tReady }) => {
  const changeLanguage = (lang: string) => {
    i18n.changeLanguage(lang);
  };

  return (
    <>
      <BrowserRouter>
        <Switch>
          {routes.map(({ exact, path, component: C, ...rest }, key) => (
            <Route
              key={key}
              exact={exact}
              path={path}
              render={(props) => <C {...props} {...rest} />}
            />
          ))}
          <Route render={() => <Redirect to="/" />} />
        </Switch>
      </BrowserRouter>
    </>
  );
}

export default withTranslation()(App);
