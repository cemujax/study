import "../webapp/css/custom.css";

import React from "react";
import ReactDOM from "react-dom";

class DetailPage extends React.Component {
  render() {
    return (
      <div className="detail">
        상세 페이지
        <ul>
          <li>Item1</li>
          <li>Item2</li>
          <li>Item3</li>
        </ul>
      </div>
    );
  }
}

ReactDOM.render(<DetailPage />, document.getElementById("root"));
