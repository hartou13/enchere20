import React, { Component } from 'react'

interface Props {
  title: string
  header: string[]
  data: any[]
  border?: number
  cellSpacing?: string
  className?: string
}

interface State {
  header: string[]
  data: any[]
}

export default class TableComp extends Component<Props, State> {
  constructor(props: Props) {
    super(props)
    this.state = {
      header: props.header,
      data: props.data,
    }
  }
  render() {
    return (
            <div className="table-responsive">
              <table
                border={this.props.border}
                cellSpacing={this.props.cellSpacing}
                className={this.props.className}
              >
                <thead>
                  <tr>
                    {this.props.header.map((i) => (
                      <th key={i} className="col">
                        {i}
                      </th>
                    ))}
                  </tr>
                </thead>
                <tbody>
                  {this.props.data.map((d) => (
                    <tr>
                      {this.props.header.map((j) => (
                        <td>{d[j]}</td>
                      ))}
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
    )
  }
}
