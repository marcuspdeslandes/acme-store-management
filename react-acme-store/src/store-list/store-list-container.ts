import { connect } from 'react-redux';
import { execute, onChangeCurrentFilterValue, changeName, nameToChange, onChangeCurrentStoreName } from './redux/actions';
import StoreListComponent from './store-list-component';

const mapStateToProps = (state: any, ownProps: any) => ({
  store: state.store
});

export default connect(mapStateToProps, { execute, onChangeCurrentFilterValue, changeName, nameToChange, onChangeCurrentStoreName })(StoreListComponent);
