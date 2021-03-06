package com.camelot.im.config.shiro;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.DispatcherType;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * . shiro配置初始化类
 */
@Configuration
public class ShiroConfig {

  /**
   * LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
   * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。 主要是AuthorizingRealm类的子类。
   */
  @Bean(name = "lifecycleBeanPostProcessor")
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }


  /**
   * shiroFilter注册.
   */
  @Bean
  public FilterRegistrationBean filterRegistrationBean() {
    FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
    DelegatingFilterProxy proxy = new DelegatingFilterProxy("shiroFilter");
    proxy.setTargetFilterLifecycle(true);
    filterRegistration.setFilter(proxy);
    filterRegistration.setEnabled(true);
    filterRegistration.addUrlPatterns("/*");
    filterRegistration.setDispatcherTypes(DispatcherType.REQUEST,DispatcherType.ASYNC,DispatcherType.FORWARD);
    return filterRegistration;
  }


  /**
   * ShiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。 它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。
   */
  @Bean(name = "shiroFilter")
  public ShiroFilterFactoryBean shiroFilterFactoryBean() {
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    shiroFilterFactoryBean.setSecurityManager(securityManager());
    //拦截器
//    Map<String, Filter> filters = new LinkedHashMap<>();
//    LogoutFilter logoutFilter = new LogoutFilter();
//    logoutFilter.setRedirectUrl("/login");
//    filters.put("logout",logoutFilter);
//    shiroFilterFactoryBean.setFilters(filters);

    //filterManager
    Map<String, String> filterChainDefinitionManager = new LinkedHashMap<>();
    filterChainDefinitionManager.put("/logout", "logout");
//    filterChainDefinitionManager.put("/chat/chat-log", "authc,perms[aa]");
    filterChainDefinitionManager.put("/**", "anon");
    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);

    //成功地址
    shiroFilterFactoryBean.setSuccessUrl("/");
    //无权限地址
    shiroFilterFactoryBean.setUnauthorizedUrl("/403");
    //登录地址
    shiroFilterFactoryBean.setLoginUrl("/");
    return shiroFilterFactoryBean;
  }

  /**
   * SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类。 //
   */
  @Bean(name = "securityManager")
  public DefaultWebSecurityManager securityManager() {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(shiroRealm());
//        securityManager.setCacheManager(ehCacheManager());
    return securityManager;
  }

  /**
   * @see DefaultWebSessionManager
   */
  @Bean(name = "sessionManager")
  public DefaultWebSessionManager defaultWebSessionManager() {
    DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//    sessionManager.setCacheManager(cacheManager());
    sessionManager.setGlobalSessionTimeout(1800000);
    sessionManager.setDeleteInvalidSessions(true);
    sessionManager.setSessionValidationSchedulerEnabled(true);
    sessionManager.setDeleteInvalidSessions(true);
    return sessionManager;
  }


  /**
   * ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm， 负责用户的认证和权限的处理，可以参考JdbcRealm的实现。
   */
  @Bean(name = "shiroRealm")
  @DependsOn("lifecycleBeanPostProcessor")
  public ShiroRealm shiroRealm() {
    ShiroRealm realm = new ShiroRealm();
//    realm.setCredentialsMatcher(hashedCredentialsMatcher());
    return realm;
  }


  /**
   * DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
   */
  @Bean
  @ConditionalOnMissingBean
  public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
    DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
    defaultAAP.setProxyTargetClass(true);
    return defaultAAP;
  }

  /**
   * AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类， 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。
   */
  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
    AuthorizationAttributeSourceAdvisor aASA = new AuthorizationAttributeSourceAdvisor();
    aASA.setSecurityManager(securityManager());
    return aASA;
  }

/**.
 * HashedCredentialsMatcher，这个类是为了对密码进行编码的， 防止密码在数据库里明码保存，当然在登陆认证的时候， 这个类也负责对form里输入的密码进行编码。
 */
//  @Bean(name = "hashedCredentialsMatcher")
//  public HashedCredentialsMatcher hashedCredentialsMatcher() {
//    HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//    credentialsMatcher.setHashAlgorithmName("MD5");
//    credentialsMatcher.setHashIterations(2);
//    credentialsMatcher.setStoredCredentialsHexEncoded(true);
//    return credentialsMatcher;
//  }


}
