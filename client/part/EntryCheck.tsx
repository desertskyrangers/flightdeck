import React from "react"

export default function EntryCheck(props) {

  const isRequired: boolean = props.required
  const help: string = !props.help ? '' : '(' + props.help + ')'
  const text: string = props.text + (isRequired ? ' *' : '')

  return (
      <div className='hbox'>
        <input id={props.id}
               name={props.id}
               type='checkbox'
               min={props.min}
               max={props.max}
               placeholder={props.text}
               autoCapitalize='none'
               autoCorrect='off'
               autoFocus={props.autoFocus}
               checked={props.checked}
               value={props.value}
               onChange={props.onChange}
               onKeyDown={props.onKeyDown}/>
        {
          !!props.text ?
            <div className='page-label-row'>
              <label htmlFor={props.id}>{text}</label>
              {/* EntryCheck should not have icon other than the checkbox */}
              {props.labelActionIcon ? <span className='icon page-label-action-button' onClick={props.onLabelAction}>{props.labelActionIcon}</span> : null}
            </div> : null
        }
        {
          !!help ?
            <div>
              <label className='page-label'>{help}</label>
            </div> : null
        }
        {props.fieldActionIcon ? <button className='icon page-field-action-button' title={props.fieldActionTitle} onClick={props.onFieldAction}>{props.fieldActionIcon}</button> : null}
      </div>
  )

}
