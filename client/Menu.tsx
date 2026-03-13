import Icon from './util/Icon.tsx'
import './css/nav.css'
import {Link} from "react-router";
import AppPath from "./AppPath";
import React from 'react';

export default function Menu() {

  return (
    <div className='nav-bar'>
      <NavButton to={AppPath.HOME} icon={Icon.FLIGHTDECK}/>
      <NavButton to={AppPath.USER_FLIGHTS} icon={Icon.FLIGHTS}/>
      <NavButton to={AppPath.SETUP} icon={Icon.SETUP}/>
      <NavButton to={AppPath.USER} icon={Icon.USER}/>
    </div>
  )

}

function NavButton(props: any) {

  return (
    <Link to={props.to}>
      <div className='nav-button'>{props.icon}</div>
    </Link>
  )

}
