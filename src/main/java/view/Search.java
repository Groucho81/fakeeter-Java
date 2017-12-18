package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import controller.PostController;
import controller.UserController;
import model.Post;
import model.User;

@Named
@Stateless
public class Search {
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
		this.searchValue = searchValue;
	}
	public List<Post> getUserPosts() {
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String id = params.get("uid");
		if (id == null) {
			List<Post> emptyList = new ArrayList<Post>();
			return emptyList;
		}else {
			int uid= Integer.parseInt(id);
			User user = usrCont.byId(uid);
			//searchResult=null;
			id=null;
			return postCont.getUserPosts(user);
		}
	}
	public void searchUsers(){
		if (searchValue!=null && !searchValue.isEmpty()) {			
			searchResult = usrCont.searchUsers(searchValue);
			searchValue = null;
		}
	}
}
