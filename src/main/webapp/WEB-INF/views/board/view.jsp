<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
  <h2>상세보기</h2>  
  <table class="table table-bordered">
    <thead>
      <tr>
        <th>${view.TITLE }</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><pre name="content" id="editor" rows="10" cols="100" style="width:766px; height:412px;">${view.CONTENT }</pre></td>
      </tr>
    </tbody>
  </table>  
 
</div>