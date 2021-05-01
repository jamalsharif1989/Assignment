package com.android.articleapp.view.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android.articleapp.di.Injectable
import com.android.articleapp.utils.NavigationCommand
import com.android.articleapp.view.listeners.BackButtonHandlerListener
import com.android.articleapp.view.listeners.BackPressListener
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/****
 * Parent for all the UI fragments. All the common things to be kept here
 * Author: Jamal
 * Company: Article App
 * Created on: 05/01/21
 * Modified on: 05/01/21
 *****/
abstract class BaseFragment<V : ViewModel, D : ViewDataBinding> : androidx.fragment.app.Fragment(), Injectable, BackPressListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: V

    protected lateinit var dataBinding: D

    private var backButtonHandler: BackButtonHandlerListener? = null

    @get:LayoutRes
    protected abstract val layoutRes: Int

    abstract val bindingVariable: Int

    protected abstract fun getViewModel(): Class<V>

    abstract fun getTitle(): String

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel())
        subscribeToNetworkLiveData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        dataBinding.setLifecycleOwner(this)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.setVariable(bindingVariable, viewModel)
        dataBinding.executePendingBindings()
        subscribeToNavigationLiveData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setTitle()
        observeLoadingStatus()
    }


    /**
     * Method which sets the title of the view
     */
    private fun setTitle() {
        activity?.let {
            when (it) {
                is BaseActivity<*, *> ->
                    it.setTitle(getTitle())
            }
        }
    }


    /**
     * Method which indicates if the fragment has header
     * Default (true): considering all fragment has header
     */
    open fun hasHeader(): Boolean {
        return true
    }

    /**
     * Method to override the backpress behaviour on indivitual fragment
     */
    override fun onBackPress(): Boolean {
        return false
    }

    override fun onResume() {
        super.onResume()
        backButtonHandler?.addBackpressListener(this)
    }

    override fun onPause() {
        super.onPause()
        backButtonHandler?.removeBackpressListener(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        backButtonHandler = context as BackButtonHandlerListener
    }

    override fun onDetach() {
        super.onDetach()
        backButtonHandler?.removeBackpressListener(this)
        backButtonHandler = null
    }


    open fun observeLoadingStatus() {
        // Implementation goes on the child fragments
    }

    open fun subscribeToNetworkLiveData() {
        // The Method is empty in Base class and cannot be abstract
    }

    open fun subscribeToNavigationLiveData() {

        (viewModel as BaseViewModel).navigationCommands.observe(viewLifecycleOwner, {

            when (it) {
                is NavigationCommand.To -> findNavController().navigate(it.directions)
                is NavigationCommand.Back -> findNavController().popBackStack()
            }
        })
    }

}