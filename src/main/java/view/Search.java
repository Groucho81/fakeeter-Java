package view;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import controller.PostController;
import controller.UserController;
import model.Post;
import model.User;

@Named
@Stateless
public class Search {
	//HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	//private String id=origRequest.getParameter("uid");
	//private Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	//private String id = params.get("uid");
	private String searchValue;
	
	@Inject
	private PostController postCont;
	@Inject
	private UserController usrCont;
	
	private List<User> searchResult;
	

	public List<User> getSearchResult() {
		return searchResult;
	}
	public void setSearchResult(List<User> searchResult) {
		this.searchResult = searchResult;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		System.out.println("##### seteamos la busqueda a" + searchValue);
		this.searchValue = searchValue;
	}
	public List<Post> getUserPosts() {
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String id = params.get("uid");
		System.out.println("##### getUserPosts-> Usuario con id: " + id);
		int uid= Integer.parseInt(id);
		User user = usrCont.byId(uid);
		return postCont.getUserPosts(user);
	}
	public void searchUsers(){
		System.out.println("##### Se ejecuta la busqueda de" + searchValue);
		if (searchValue!=null && !searchValue.isEmpty()) {			
			searchResult = usrCont.searchUsers(searchValue);
			System.out.println("##### Encontre: "+searchResult.size());
		}
	}
}
