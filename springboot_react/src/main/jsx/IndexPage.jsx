import "../webapp/css/custom.css";

import React from "react";
import ReactDOM from "react-dom";

class IndexPage extends React.Component {
  render() {
    return (
      <div className="index">
        메인 페이지
        <p>
          <a href="detail.html">상세 페이지</a>
        </p>
      </div>
    );
  }
}

ReactDOM.render(<IndexPage />, document.getElementById("root"));
