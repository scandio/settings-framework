/* global $, AJS*/

(function($, ready) {

  ready(function() {
    $('.settings').each(function() {
      var $settings = $(this);

      var $spinner = $settings.find('> .spinner');
      var $form = $settings.find('> form');

      var $submitButton = $form.find('.submit');
      var $formSpinner = $form.find('.spinner');
      var $successIcon = $form.find('.success');

      var url = $settings.data('url');

      var disableForm = function() {
        $form.find('input').attr('disabled', 'disabled');
        $form.find('a').css('pointer-events', 'none');
        $formSpinner.show();
      };

      var enableForm = function() {
        $form.find('input').removeAttr('disabled');
        $form.find('a').css('pointer-events', 'auto');
        $formSpinner.hide();
      };

      var clearValues = function() {
        $form.find('input[type=text],input[type=checkbox]').each(function() {
          var $input = $(this);
          if ($input.is(':checkbox')) {
            $input.attr('checked', false);
          } else {
            $input.val('');
          }
        });
      };

      var fillValuesIntoForm = function(values) {
        Object.keys(values).forEach(function(key) {
          var $input = $('#' + key);
          if ($input.is(':checkbox')) {
            if (values[key] === 'true') {
              $input.attr('checked', true);
            }
          } else {
            $input.val(values[key]);
          }
        });
      };

      $.ajax({
        url: url,
        success: function(initialValues){
          fillValuesIntoForm(initialValues);
          $spinner.hide();
          $form.show();

          $submitButton.click(function(e) {
            e.preventDefault();

            var newValues = $form.serializeArray()
              .concat($('input:checkbox:not(:checked)').map(function() {
                return {name: this.name, value: this.checked ? this.value : 'false'};
              }).get())
              .reduce(function(obj, item) {
                obj[item.name] = item.value;
                return obj;
              }, {});

            disableForm();

            $.ajax({
              url: url,
              type: 'PUT',
              data: JSON.stringify(newValues),
              contentType: 'application/json; charset=utf-8',
              dataType: 'json',
              success: function(storedValues) {
                clearValues();
                fillValuesIntoForm(storedValues);
                enableForm();
                $successIcon.show();
                setTimeout(function() {
                  $successIcon.fadeOut('slow');
                }, 1000);
              }
            });

          });
        }
      });
    });
  });
})(typeof AJS === 'undefined' ? $ : AJS.$, typeof AJS === 'undefined' ? $(document).ready : AJS.toInit);