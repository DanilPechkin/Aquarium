package com.danilp.aquariumhelper.presentation.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.danilp.aquariumhelper.R
import com.danilp.aquariumhelper.domain.use_case.validation.ValidationErrorCode
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    value: String,
    onValueChange: (String) -> Unit,
    isSearchFieldVisible: Boolean,
    hideSearchField: () -> Unit,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(visible = isSearchFieldVisible) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { hideSearchField() }),
            placeholder = {
                Text(text = stringResource(R.string.search_placeholder))
            },
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                focusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = modifier
                .focusRequester(focusRequester)
                .sizeIn(maxWidth = 192.dp)
        )

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AquariumTopBar(
    title: String,
    switchMenuVisibility: () -> Unit,
    isMenuExpanded: Boolean,
    hideMenu: () -> Unit,
    navigateBack: () -> Unit,
    navigateToSettings: () -> Unit,
    navigateToAccount: () -> Unit
) {
    TopAppBar(title = {
        Text(
            text = title,
            modifier = Modifier.padding(horizontal = 4.dp),
            maxLines = 1
        )
    },
        navigationIcon = {
            IconButton(
                onClick = navigateBack
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.content_description_back_arrow)
                )
            }
        },
        actions = {
            Box {
                IconButton(
                    onClick = switchMenuVisibility
                ) {
                    Icon(
                        imageVector = Icons.Rounded.MoreVert,
                        contentDescription = stringResource(
                            R.string.expand_upbar_menu_content_description
                        )
                    )
                }
                DropdownMenu(
                    expanded = isMenuExpanded,
                    onDismissRequest = hideMenu,
                    offset = DpOffset((10).dp, 0.dp)
                ) {
                    DropdownMenuItem(
                        onClick = navigateToAccount,
                        text = {
                            Text(
                                text = stringResource(
                                    R.string.account_menu_item_content_description
                                )
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.AccountCircle,
                                contentDescription = stringResource(
                                    R.string.account_menu_item_content_description
                                )
                            )
                        }
                    )
                    DropdownMenuItem(
                        onClick = navigateToSettings,
                        text = {
                            Text(
                                text = stringResource(
                                    R.string.settings_menu_item_content_description
                                )
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.Settings,
                                contentDescription = stringResource(
                                    R.string.settings_menu_item_content_description
                                )
                            )
                        }
                    )
                }
            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AquariumTopBarWithSearch(
    title: String,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    isSearchFieldVisible: Boolean,
    switchSearchFieldVisibility: () -> Unit,
    hideSearchField: () -> Unit,
    searchFieldFocusRequester: FocusRequester,
    switchMenuVisibility: () -> Unit,
    isMenuExpanded: Boolean,
    hideMenu: () -> Unit,
    navigateBack: () -> Unit,
    navigateToSettings: () -> Unit,
    navigateToAccount: () -> Unit
) {
    TopAppBar(title = {
        Text(
            text = title,
            modifier = Modifier.padding(horizontal = 4.dp),
            maxLines = 1
        )
    },
        navigationIcon = {
            IconButton(
                onClick = navigateBack
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.content_description_back_arrow)
                )
            }
        },
        actions = {
            SearchField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                isSearchFieldVisible = isSearchFieldVisible,
                hideSearchField = hideSearchField,
                focusRequester = searchFieldFocusRequester
            )

            IconButton(
                onClick = switchSearchFieldVisibility
            ) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = stringResource(
                        R.string.search_icon
                    )
                )
            }

            Box {
                IconButton(
                    onClick = switchMenuVisibility
                ) {
                    Icon(
                        imageVector = Icons.Rounded.MoreVert,
                        contentDescription = stringResource(
                            R.string.expand_upbar_menu_content_description
                        )
                    )
                }
                DropdownMenu(
                    expanded = isMenuExpanded,
                    onDismissRequest = hideMenu,
                    offset = DpOffset((10).dp, 0.dp)
                ) {
                    DropdownMenuItem(
                        onClick = navigateToAccount,
                        text = {
                            Text(
                                text = stringResource(
                                    R.string.account_menu_item_content_description
                                )
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.AccountCircle,
                                contentDescription = stringResource(
                                    R.string.account_menu_item_content_description
                                )
                            )
                        }
                    )
                    DropdownMenuItem(
                        onClick = navigateToSettings,
                        text = {
                            Text(
                                text = stringResource(
                                    R.string.settings_menu_item_content_description
                                )
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.Settings,
                                contentDescription = stringResource(
                                    R.string.settings_menu_item_content_description
                                )
                            )
                        }
                    )
                }
            }
        })
}

@Composable
fun GridItem(
    name: String,
    message: String,
    cardColors: CardColors,
    imageUri: String,
    modifier: Modifier = Modifier
) {
    Card(
        colors = cardColors,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        GlideImage(
            imageModel = imageUri.ifBlank { (R.drawable.aquairum_pic) },
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .heightIn(max = 110.dp)
        )
        Column(
            modifier = Modifier.padding(top = 6.dp, start = 10.dp, bottom = 10.dp, end = 10.dp)
        ) {
            Text(text = name, style = MaterialTheme.typography.titleMedium, maxLines = 1)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = message, style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Composable
fun ImagePicker(
    imageUri: String,
    onSelection: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val pickMedia = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) {
        onSelection(it?.toString() ?: "")
    }

    GlideImage(
        imageModel = imageUri.ifBlank { (R.drawable.aquairum_pic) },
        contentDescription = stringResource(R.string.imagepicker_content_descr),
        contentScale = ContentScale.Crop,
        // TODO: placeholder,
        modifier = modifier
            .clickable {
                pickMedia.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordFieldWithError(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    changePasswordVisibility: () -> Unit,
    isPasswordVisible: Boolean,
    modifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
    errorCode: Int? = null,
    maxLines: Int = Int.MAX_VALUE,
    singleLine: Boolean = false
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(text = label)
            },
            modifier = textFieldModifier,
            isError = errorCode != null,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = if (isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            maxLines = maxLines,
            singleLine = singleLine,
            trailingIcon = {
                val image = if (isPasswordVisible) {
                    Icons.Rounded.Visibility
                } else {
                    Icons.Rounded.VisibilityOff
                }

                val description = if (isPasswordVisible) {
                    stringResource(R.string.hide_password_descr)
                } else {
                    stringResource(R.string.show_password_descr)
                }

                IconButton(onClick = changePasswordVisibility) {
                    Icon(
                        imageVector = image,
                        contentDescription = description
                    )
                }
            }
        )

        if (errorCode != null) {
            Text(
                text = when (errorCode) {
                    ValidationErrorCode.BLANK_FIELD_ERROR -> {
                        stringResource(R.string.this_field_cant_be_blank_error)
                    }
                    ValidationErrorCode.PASSWORD_IS_SHORT_ERROR -> {
                        stringResource(R.string.password_length_error)
                    }
                    ValidationErrorCode.PASSWORD_PATTERN_ERROR -> {
                        stringResource(R.string.password_pattern_error)
                    }
                    ValidationErrorCode.REPEAT_PASSWORD_ERROR -> {
                        stringResource(R.string.passwords_not_match_error)
                    }
                    else -> {
                        stringResource(R.string.unknown_error)
                    }
                },
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoFieldWithError(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
    errorCode: Int? = null,
    maxLines: Int = Int.MAX_VALUE,
    singleLine: Boolean = false
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(text = label)
            },
            modifier = textFieldModifier,
            isError = errorCode != null,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            maxLines = maxLines,
            singleLine = singleLine
        )

        if (errorCode != null) {
            Text(
                text = when (errorCode) {
                    ValidationErrorCode.BLANK_FIELD_ERROR -> {
                        stringResource(R.string.this_field_cant_be_blank_error)
                    }
                    ValidationErrorCode.DECIMAL_ERROR -> {
                        stringResource(R.string.should_be_decimal_error)
                    }
                    ValidationErrorCode.INTEGER_ERROR -> {
                        stringResource(R.string.should_be_integer_error)
                    }
                    ValidationErrorCode.NEGATIVE_VALUE_ERROR -> {
                        stringResource(R.string.this_value_cant_be_negative_error)
                    }
                    ValidationErrorCode.EMAIL_PATTERN_ERROR -> {
                        stringResource(R.string.email_is_not_valid_error)
                    }
                    else -> {
                        stringResource(R.string.unknown_error)
                    }
                },
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun FromToInfoFields(
    label: String,
    valueFrom: String,
    valueTo: String,
    onValueFromChange: (String) -> Unit,
    onValueToChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Decimal,
        imeAction = ImeAction.Next
    ),
    keyboardActionsFrom: KeyboardActions = KeyboardActions(),
    keyboardActionsTo: KeyboardActions = KeyboardActions(),
    errorCodeFrom: Int? = null,
    errorCodeTo: Int? = null
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            modifier = Modifier.padding(8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoFieldWithError(
                value = valueFrom,
                onValueChange = onValueFromChange,
                label = stringResource(R.string.label_from),
                modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(1f),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActionsFrom,
                errorCode = errorCodeFrom,
                maxLines = 1,
                singleLine = true
            )
            InfoFieldWithError(
                value = valueTo,
                onValueChange = onValueToChange,
                label = stringResource(R.string.label_to),
                modifier = Modifier.weight(1f),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActionsTo,
                errorCode = errorCodeTo,
                maxLines = 1,
                singleLine = true
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedDropDownMenuField(
    label: String,
    items: List<String>,
    selectedItem: String,
    changeSelectedItem: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Rounded.KeyboardArrowUp
    else
        Icons.Rounded.KeyboardArrowDown

    Column(modifier = modifier) {

        OutlinedTextField(
            value = selectedItem,
            onValueChange = { },
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            maxLines = 1,
            singleLine = true,
            readOnly = true,
            label = { Text(text = label) },
            trailingIcon = {
                IconButton(
                    onClick = { expanded = !expanded }
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = stringResource(R.string.expand_button_content_descr)
                    )
                }
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(
                    with(LocalDensity.current) {
                        textFieldSize.width.toDp()
                    }
                )
        ) {
            items.forEach { label ->
                DropdownMenuItem(
                    text = { Text(text = label) },
                    onClick = {
                        changeSelectedItem(label)
                        expanded = false
                    }
                )
            }
        }

    }
}