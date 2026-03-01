import React, {useState} from 'react'
import {TwitterPicker} from 'react-color'
import Numbers from '../util/Numbers'
import '../css/react-color.css'

export default function EntryColor(props) {

	const isRequired: boolean = props.required
	const help: string = !props.help ? '' : '(' + props.help + ')'
	const text: string = props.text + (isRequired ? ' *' : '')

	const [colorPickerVisible, setColorPickerVisible] = useState<boolean>(props.colorPickerVisibile || false)

	function toggleColorPicker() {
		setColorPickerVisible(!colorPickerVisible)
	}

	function closeColorPicker() {
		setColorPickerVisible(false)
	}

	function changeColor(color) {
		setColorPickerVisible(false)
		props.onChange({target: {value: fromReactColor(color)}})
	}

	function toReactColor(color) {
		const r = Number.parseInt(color.substring(0, 2), 16);
		const g = Number.parseInt(color.substring(2, 2), 16);
		const b = Number.parseInt(color.substring(4, 2), 16);
		const a = Number.parseInt(color.substring(6, 2), 16);
		return {r: r, g: g, b: b, a: a};
	}

	function fromReactColor(reactColor) {
		const r = Numbers.decimalToHex(reactColor.rgb.r, 2)
		const g = Numbers.decimalToHex(reactColor.rgb.g, 2)
		const b = Numbers.decimalToHex(reactColor.rgb.b, 2)
		const a = Numbers.decimalToHex(255 * reactColor.rgb.a, 2)
		return "#" + r + g + b + a;
	}

	const colors = [
		"#800000", "#804000", "#808000", "#008000", "#000080", "#400080", "#000000",
		"#ff0000", "#ff8000", "#ffff00", "#00ff00", "#0000ff", "#8000ff", "#808080",
		"#ffa0a0", "#ffd0a0", "#ffffa0", "#a0ffa0", "#a0a0ff", "#d0a0ff", "#ffffff"
	]

	return (
		<div>
			{
				!!props.text ?
					<div className='page-label-row'>
						<label htmlFor={props.id} className='page-label'>{text}</label>
						<span className='icon' onClick={props.onIconClick}>{props.icon}</span>
						{props.labelActionIcon ? <span className='icon page-label-action-button' onClick={props.onLabelAction}>{props.labelActionIcon}</span> : null}
					</div> : null
			}
			{
				!!help ?
					<div className='page-label-row'>
						<label className='page-label'>{help}</label>
					</div> : null
			}

			<div className='hbox swatch' onClick={toggleColorPicker}>
				<span style={{'background': props.value}} className='swatch-color'>&nbsp;</span>
				{props.fieldActionIcon ? <button className='icon page-field-action-button' title={props.fieldActionTitle} onClick={props.onFieldAction}>{props.fieldActionIcon}</button> : null}
			</div>

			{colorPickerVisible ?
				<div className='popover'>
					<div className='popover-cover' onClick={closeColorPicker}/>
					<div className='hbox'><TwitterPicker itemStyle={{'background':'red'}} triangle='hide' colors={colors} color={toReactColor(props.value)} onChangeComplete={changeColor}/></div>
				</div>
				: null
			}
		</div>
	)

}
