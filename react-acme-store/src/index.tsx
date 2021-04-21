import * as React from "react";
import { render } from "react-dom";
import {createStore, applyMiddleware, Store, combineReducers} from "redux";
import { Provider } from "react-redux";
import thunk from "redux-thunk";

import App from "./App";
import storeReducer from "./store-list/redux/reducer";

const reducer =
combineReducers({
    store:storeReducer
});

const store: Store<any, any> & {
    dispatch: DispatchType;
} = createStore(reducer, applyMiddleware(thunk));

const rootElement = document.getElementById("root");
render(
    <Provider store={store}>
        <App />
    </Provider>,
    rootElement
);
